package com.dou.server.service;

import com.dou.server.exception.LogicException;
import com.dou.server.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
public interface BaseService <T extends BaseEntity> {

    List<T> getList(T temp);

    @Transactional(rollbackFor = Exception.class)
    void add(T temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void update(T temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void delete(Collection<?> ids) throws LogicException;
}