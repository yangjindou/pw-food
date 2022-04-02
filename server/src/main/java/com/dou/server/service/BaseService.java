package com.dou.server.service;

import com.dou.server.exception.LogicException;
import com.dou.server.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
public interface BaseService <T extends BaseEntity> {

    @Transactional(rollbackFor = Exception.class)
    void delete(List<?> ids) throws LogicException;
}