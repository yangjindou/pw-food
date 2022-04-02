package com.dou.server.service;

import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangjd
 */
public interface DictService extends BaseService<Dict> {

    @Transactional(rollbackFor = Exception.class)
    void add(Dict temp) throws Exception;

    PageInfo<Dict> getPage(Pagination pagination, Dict temp);
}
