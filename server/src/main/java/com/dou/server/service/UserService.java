package com.dou.server.service;

import com.dou.server.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangjd
 */
public interface UserService extends BaseService<User> {

    User getById(Integer id);

    User verifyUser(User temp);

    @Transactional(rollbackFor = Exception.class)
    void passwordModify(String oldPwd, String newPwd);

    @Transactional(rollbackFor = Exception.class)
    void register(User temp);
}