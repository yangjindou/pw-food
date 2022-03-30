package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.UserMapper;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import com.dou.server.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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
        if (!CommonUtils.varIsBlank(temp.getLoginName())) {
            criteria.andLike("login_name", String.format("%%%s%%",temp.getLoginName()));
        }
        if (!CommonUtils.varIsBlank(temp.getUserName())) {
            criteria.andLike("user_name", String.format("%%%s%%",temp.getUserName()));
        }
        List<User> userList = userMapper.selectByExample(example);
        userList.forEach(User::protectInfo);
        return new PageInfo<>(userList);
    }

    @Override
    public User verifyUser(User temp) throws Exception {
        User selectUser = new User();
        selectUser.setLoginName(temp.getLoginName());
        User user = userMapper.selectOne(selectUser);
        if (null == user) {
            throw new LogicException("用户不存在");
        }
        if (!SecurityUtils.isPasswordValid(user.getPassword(), temp.getPassword(), user.getSalt())) {
            throw new LogicException("密码错误");
        }
        user.createToken();
        redisUtils.set(RedisUtils.USER_PREFIX + user.getId(),user);
        user.protectInfo();
        return user;
    }

    @Override
    public void add(User temp) throws Exception {
        if (CommonUtils.varIsBlank(temp.getPassword())) {
            temp.setPassword("123456");
        }
        String salt = SecurityUtils.randomSalt(16);
        temp.setSalt(salt);
        temp.setPassword(SecurityUtils.encode(temp.getPassword(), salt));
        temp.setCreateUser(User.getRequestUser().getId());
        temp.setCreateDate(new Date());
        if (userMapper.insert(temp) == 0) {
            throw new LogicException("新增失败");
        }
    }

    @Override
    public void passwordModify(String oldPwd, String newPwd) throws Exception {
        User requestUser = User.getRequestUser();
        if (!SecurityUtils.isPasswordValid(requestUser.getPassword(), oldPwd, requestUser.getSalt())) {
            throw new LogicException("旧密码错误");
        }
        User updateUser = new User();
        updateUser.setId(requestUser.getId());
        String salt = SecurityUtils.randomSalt(16);
        updateUser.setSalt(salt);
        updateUser.setPassword(SecurityUtils.encode(newPwd, salt));
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
        temp.setPassword(null);
        temp.setUpdateUser(User.getRequestUser().getId());
        temp.setUpdateDate(new Date());
        if (userMapper.updateByPrimaryKeySelective(temp) == 0) {
            throw new LogicException("修改失败");
        }
    }

    @Override
    public void delete(List<?> ids) throws LogicException {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        if (userMapper.deleteByExample(example) != ids.size()) {
            throw new LogicException("新增失败");
        }
    }
}
