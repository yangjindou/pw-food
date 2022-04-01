package com.dou.server.service;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangjd
 */
public interface DictService {

    @Transactional(rollbackFor = Exception.class)
    void add(Dict temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void update(Dict temp) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    void delete(List<?> ids) throws LogicException;

    PageInfo<Dict> getPage(Pagination pagination, Dict temp);
}
