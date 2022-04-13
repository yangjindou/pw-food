package com.dou.server.service;

import com.dou.server.model.Warehouse;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseService extends BaseService<Warehouse> {

    List<Warehouse> getList(Warehouse temp);
}