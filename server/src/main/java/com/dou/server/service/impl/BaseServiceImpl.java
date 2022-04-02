package com.dou.server.service.impl;

import com.dou.server.exception.LogicException;
import com.dou.server.model.BaseEntity;
import com.dou.server.service.BaseService;
import com.dou.server.sql.ICriteria;
import com.dou.server.tag.MyMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Resource
    private MyMapper<T> mapper;

    @Override
    public void delete(List<?> ids) throws LogicException {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(tClass);
        ICriteria criteria = new ICriteria(example);
        criteria.andIn("id",ids);
        if (mapper.deleteByExample(example) != ids.size()) {
            throw new LogicException("删除失败");
        }
    }
}
