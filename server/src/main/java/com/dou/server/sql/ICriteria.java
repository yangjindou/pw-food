package com.dou.server.sql;

import com.dou.server.utils.CommonUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
public class ICriteria {

    private final Example.Criteria criteria;

    public ICriteria(Example example) {
        criteria  = example.createCriteria();
    }

    public ICriteria andEqualTo(String property, Object value) {
        if (!CommonUtils.varIsBlank(value)) {
            criteria.andEqualTo(property, value);
        }
        return this;
    }

    public ICriteria andLike(String property, Object value) {
        if (!CommonUtils.varIsBlank(value)) {
            criteria.andEqualTo(property, SqlUtils.concatLike(value, SqlLike.DEFAULT));
        }
        return this;
    }

    public ICriteria andLike(String property, Object value, SqlLike type) {
        if (!CommonUtils.varIsBlank(value)) {
            criteria.andEqualTo(property, SqlUtils.concatLike(value, type));
        }
        return this;
    }

    public Example.Criteria getCriteria() {
        return criteria;
    }
}
