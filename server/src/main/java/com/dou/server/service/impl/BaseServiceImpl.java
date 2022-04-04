package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.model.BaseEntity;
import com.dou.server.model.User;
import com.dou.server.service.BaseService;
import com.dou.server.sql.ICriteria;
import com.dou.server.tag.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    // 必须用@Autowired，不然载入不了
    @Autowired
    private MyMapper<T> mapper;

    @Override
    public List<T> getList(T temp) {
        return mapper.select(temp);
    }

    @Override
    public void add(T temp) {
        temp.setCreateUser(User.getRequestUser().getId()).setCreateDate(new Date());
        if (mapper.insert(temp) == 0) {
            throw new LogicException("新增失败");
        }
    }

    @Override
    public void update(T temp) {
        temp.setUpdateUser(User.getRequestUser().getId()).setUpdateDate(new Date());
        if (mapper.updateByPrimaryKeySelective(temp) == 0) {
            throw new LogicException("修改失败");
        }
    }

    @Override
    public void delete(Collection<?> ids) {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(tClass);
        ICriteria criteria = new ICriteria(example);
        criteria.andIn("id",ids);
        if (mapper.deleteByExample(example) != ids.size()) {
            throw new LogicException("删除失败");
        }
    }
}
