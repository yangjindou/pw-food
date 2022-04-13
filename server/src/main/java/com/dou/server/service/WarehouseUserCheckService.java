package com.dou.server.service;

import com.dou.server.model.WarehouseUserCheck;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserCheckService extends BaseService<WarehouseUserCheck> {

    List<WarehouseUserCheck> getList(WarehouseUserCheck temp);
}