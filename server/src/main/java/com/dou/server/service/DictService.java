package com.dou.server.service;

import com.dou.server.model.Dict;
import com.dou.server.model.Pagination;
import com.github.pagehelper.PageInfo;

/**
 * @author yangjd
 */
public interface DictService extends BaseService<Dict> {

    PageInfo<Dict> getPage(Pagination pagination, Dict temp);
}
