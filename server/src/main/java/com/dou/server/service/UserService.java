package com.dou.server.service;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangjd
 */
public interface UserService {

    User get(User temp);

    User verifyUser(User temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void add(User temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void passwordModify(String oldPwd, String newPwd) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void update(User temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void delete(List<?> ids) throws LogicException;

    PageInfo<User> getPage(Pagination pagination, User temp);
}
