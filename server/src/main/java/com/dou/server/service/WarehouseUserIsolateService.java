package com.dou.server.service;

import com.dou.server.model.WarehouseUserIsolate;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserIsolateService extends BaseService<WarehouseUserIsolate> {

    List<WarehouseUserIsolate> getList(WarehouseUserIsolate temp);
}