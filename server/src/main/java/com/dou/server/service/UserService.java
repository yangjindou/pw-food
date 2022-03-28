package com.dou.server.service;

import com.dou.server.mapper.UserMapper;
import com.dou.server.model.ResultEntity;
import com.dou.server.model.User;
import com.dou.server.tag.ResultEnums;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangjd
 */
public interface UserService {

    PageInfo<User> get(Integer page, Integer limit, User temp);

    ResultEntity verifyUser(User temp) throws Exception;

    ResultEnums add(User temp) throws Exception;

    ResultEnums passwordModify(String oldPwd, String newPwd) throws Exception;

    ResultEnums update(User temp) throws Exception;

    ResultEnums delete(List<Integer> ids);

    UserMapper getMapper();
}
