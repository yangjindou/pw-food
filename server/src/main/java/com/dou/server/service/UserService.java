package com.dou.server.service;

import com.dou.server.exception.LogicException;
import com.dou.server.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangjd
 */
public interface UserService {

    PageInfo<User> get(Integer page, Integer limit, User temp);

    User verifyUser(User temp) throws Exception;

    void add(User temp) throws Exception;

    void passwordModify(String oldPwd, String newPwd) throws Exception;

    void update(User temp) throws Exception;

    void delete(List<?> ids) throws LogicException;
}
