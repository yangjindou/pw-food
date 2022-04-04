package com.dou.server.service;

import com.dou.server.model.DictData;

import java.util.List;

/**
 * @author yangjd
 */
public interface DictDataService extends BaseService<DictData> {

    void add(List<DictData> dictDatas);

    List<DictData> getListByCode(String sign);
}
