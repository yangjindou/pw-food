package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.mapper.UserMapper;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.sql.ICriteria;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import com.dou.server.utils.SecurityUtils;
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
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserMapper userMapper;
    private final RedisUtils redisUtils;

    @Override
    public User getById(Integer id) {
        Example example = new Example(User.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andEqualTo("id", id);
        return userMapper.selectOneByExample(example).protectInfo();
    }

    @Override
    public List<User> getList(User temp) {
        Example example = new Example(User.class);
        ICriteria criteria = new ICriteria(example);
        criteria.andLike("loginName", temp.getLoginName())
            .andLike("userName", temp.getUserName())
            .andNotEqualTo("role", "管理员");
        List<User> userList = userMapper.selectByExample(example);
        userList.forEach(User::protectInfo);
        return userList;
    }

    @Override
    public void register(User temp) {
        temp.setRole("企业用户");
        if (userMapper.selectCount(new User().setPhone(temp.getPhone())) > 0) {
            throw new LogicException("联系电话已注册");
        }
        add(temp);
    }

    @Override
    public User verifyUser(User temp) {
        User selectUser = new User().setLoginName(temp.getLoginName());
        User user = userMapper.selectOne(selectUser);
        if (null == user) {
            throw new LogicException("账号不存在");
        }
        if (!SecurityUtils.isPasswordValid(user.getPassword(), temp.getPassword(), user.getSalt())) {
            throw new LogicException("密码错误");
        }
        if (null != user.getDisabled() && user.getDisabled()) {
            throw new LogicException("账号已被禁用");
        }
        user.createToken();
        redisUtils.set(RedisUtils.USER_PREFIX + user.getId(), user, RedisUtils.USER_EXPIRE);
        user.protectInfo();
        return user;
    }

    @Override
    public void add(User temp) {
        // 判断账号是否存在过
        if (userMapper.selectCount(new User().setLoginName(temp.getLoginName())) > 0) {
            throw new LogicException("账号已存在");
        }
        if (CommonUtils.varIsBlank(temp.getPassword())) {
            temp.setPassword("123456");
        }
        String salt = SecurityUtils.randomSalt(16);
        temp.setSalt(salt).setPassword(SecurityUtils.encode(temp.getPassword(), salt));
        super.add(temp);
    }

    @Override
    public void passwordModify(String oldPwd, String newPwd) {
        User requestUser = User.getRequestUser();
        if (!SecurityUtils.isPasswordValid(requestUser.getPassword(), oldPwd, requestUser.getSalt())) {
            throw new LogicException("旧密码错误");
        }
        User updateUser = new User().setId(requestUser.getId());
        String salt = SecurityUtils.randomSalt(16);
        updateUser.setSalt(salt).setPassword(SecurityUtils.encode(newPwd, salt));
        updateUser.setUpdateUser(requestUser.getId()).setUpdateDate(new Date());
        if (userMapper.updateByPrimaryKeySelective(updateUser) == 0) {
            throw new LogicException("密码修改失败");
        } else {
            redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        }
    }

    @Override
    public void update(User temp) {
        temp.setPassword(null);
        super.update(temp);
    }
}