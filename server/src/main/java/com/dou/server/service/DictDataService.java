package com.dou.server.service;

import com.dou.server.model.DictData;
import com.dou.server.model.Pagination;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author yangjd
 */
public interface DictDataService extends BaseService<DictData> {

    PageInfo<DictData> getPage(Pagination pagination, DictData temp);

    void add(List<DictData> dictDatas) throws Exception;
}
