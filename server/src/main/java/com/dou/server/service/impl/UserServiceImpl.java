package com.dou.server.service.impl;

import com.dou.server.mapper.UserMapper;
import com.dou.server.model.ResultEntity;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.tag.MyServerException;
import com.dou.server.tag.ResultEnums;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RSAUtils;
import com.dou.server.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangjd
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public PageInfo<User> get(Integer page, Integer limit, User temp) {
        PageHelper.startPage(page, limit);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!CommonUtils.varIsBlank(temp.getName())) {
            criteria.andLike("name",String.format("%%%s%%",temp.getName()));
        }
        List<User> userList = userMapper.selectByExample(example);
        userList.forEach(User::protectInfo);
        return new PageInfo<>(userList);
    }

    @Override
    public ResultEntity verifyUser(User temp) throws Exception {
        User selectUser = new User();
        selectUser.setName(temp.getName());
        User user = userMapper.selectOne(selectUser);
        if (null == user) {
            return new ResultEntity(ResultEnums.E_USER_MISS);
        }
        // 验证密码是否正确
        String passText = RSAUtils.decryptPass(user.getPassword(),user.getPublicKey());
        if (!temp.getPassword().equals(passText)) {
            return new ResultEntity(ResultEnums.E_USER_PASSWORD_ERROR);
        }
        user.createToken();
        redisUtils.set(RedisUtils.USER_PREFIX + user.getId(),user);
        user.protectInfo();
        return new ResultEntity(ResultEnums.CODE_SUCCESS,"验证成功",user);
    }

    @Override
    public ResultEnums add(User temp) throws Exception {
        System.out.println(RedisUtils.DEFAULT_EXPIRE);
        if (CommonUtils.varIsBlank(temp.getPassword())) {
            temp.setPassword("111223456");
        }
        Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
        temp.setPassword(map.get("password"));
        temp.setPublicKey(map.get("publicKey"));
        temp.setCreateUser(Objects.requireNonNull(User.getRequestUser()).getId());
        temp.setCreateDate(new Date());
        return userMapper.insert(temp) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }

    @Override
    public ResultEnums passwordModify(String oldPwd, String newPwd) throws Exception {
        User requestUser = User.getRequestUser();
        assert requestUser != null;
        String passText = RSAUtils.decryptPass(requestUser.getPassword(),requestUser.getPublicKey());
        if (!oldPwd.equals(passText)) {
            throw new MyServerException(ResultEnums.E_USER_PASSWORD_ERROR);
        }
        User updateUser = new User();
        updateUser.setId(requestUser.getId());
        Map<String, String> map = RSAUtils.encryptPass(newPwd);
        updateUser.setPassword(map.get("password"));
        updateUser.setPublicKey(map.get("publicKey"));
        updateUser.setUpdateUser(requestUser.getId());
        updateUser.setUpdateDate(new Date());
        userMapper.updateByPrimaryKeySelective(updateUser);
        redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        return ResultEnums.SUCCESS;
    }

    @Override
    public ResultEnums update(User temp) throws Exception {
        if (!CommonUtils.varIsBlank(temp.getPassword())) {
            Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
            temp.setPassword(map.get("password"));
            temp.setPublicKey(map.get("publicKey"));
        }
        temp.setUpdateUser(Objects.requireNonNull(User.getRequestUser()).getId());
        temp.setUpdateDate(new Date());
        return userMapper.updateByPrimaryKeySelective(temp) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }

    @Override
    public ResultEnums delete(List<Integer> ids) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        return userMapper.deleteByExample(example) > 0 ? ResultEnums.SUCCESS : ResultEnums.ERROR;
    }

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
