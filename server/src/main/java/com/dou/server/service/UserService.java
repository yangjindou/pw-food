package com.dou.server.service;

import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangjd
 */
public interface UserService extends BaseService<User> {

    User getById(Integer id);

    User verifyUser(User temp);

    @Transactional(rollbackFor = Exception.class)
    void passwordModify(String oldPwd, String newPwd);

    PageInfo<User> getPage(Pagination pagination, User temp);

    @Transactional(rollbackFor = Exception.class)
    void register(User temp);
}