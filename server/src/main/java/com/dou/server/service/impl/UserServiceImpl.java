package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.UserMapper;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RSAUtils;
import com.dou.server.utils.RedisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RedisUtils redisUtils;

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
    public User verifyUser(User temp) throws Exception {
        User selectUser = new User();
        selectUser.setName(temp.getName());
        User user = userMapper.selectOne(selectUser);
        if (null == user) {
            throw new LogicException("用户不存在");
        }
        // 验证密码是否正确
        String passText = RSAUtils.decryptPass(user.getPassword(),user.getPublicKey());
        if (!temp.getPassword().equals(passText)) {
            throw new LogicException("密码错误");
        }
        user.createToken();
        redisUtils.set(RedisUtils.USER_PREFIX + user.getId(),user);
        user.protectInfo();
        return user;
    }

    @Override
    public void add(User temp) throws Exception {
        System.out.println(RedisUtils.DEFAULT_EXPIRE);
        if (CommonUtils.varIsBlank(temp.getPassword())) {
            temp.setPassword("123456");
        }
        Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
        temp.setPassword(map.get("password"));
        temp.setPublicKey(map.get("publicKey"));
        temp.setCreateUser(Objects.requireNonNull(User.getRequestUser()).getId());
        temp.setCreateDate(new Date());
        if (userMapper.insert(temp) == 0) {
            throw new LogicException("新增失败");
        }
    }

    @Override
    public void passwordModify(String oldPwd, String newPwd) throws Exception {
        User requestUser = User.getRequestUser();
        assert requestUser != null;
        String passText = RSAUtils.decryptPass(requestUser.getPassword(),requestUser.getPublicKey());
        if (!oldPwd.equals(passText)) {
            throw new LogicException("旧密码错误");
        }
        User updateUser = new User();
        updateUser.setId(requestUser.getId());
        Map<String, String> map = RSAUtils.encryptPass(newPwd);
        updateUser.setPassword(map.get("password"));
        updateUser.setPublicKey(map.get("publicKey"));
        updateUser.setUpdateUser(requestUser.getId());
        updateUser.setUpdateDate(new Date());
        if (userMapper.updateByPrimaryKeySelective(updateUser) == 0) {
            throw new LogicException("新增失败");
        } else {
            redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        }
    }

    @Override
    public void update(User temp) throws Exception {
        if (!CommonUtils.varIsBlank(temp.getPassword())) {
            Map<String, String> map = RSAUtils.encryptPass(temp.getPassword());
            temp.setPassword(map.get("password"));
            temp.setPublicKey(map.get("publicKey"));
        }
        temp.setUpdateUser(Objects.requireNonNull(User.getRequestUser()).getId());
        temp.setUpdateDate(new Date());
        if (userMapper.updateByPrimaryKeySelective(temp) == 0) {
            throw new LogicException("修改失败");
        }
    }

    @Override
    public void delete(List<Integer> ids) throws LogicException {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        if (userMapper.deleteByExample(example) != ids.size()) {
            throw new LogicException("新增失败");
        }
    }
}
