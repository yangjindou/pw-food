package com.dou.server.tag;

import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yangjd
 */
public interface MyMapper<T> extends Mapper<T>, ExampleMapper<T> {
    //FIXME 特别注意，该接口不能被MapperScan注解扫描到，否则会报错
}