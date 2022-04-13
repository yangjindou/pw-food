package com.dou.server.service;

import com.dou.server.model.WarehouseUser;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserService extends BaseService<WarehouseUser> {

    List<WarehouseUser> getList(WarehouseUser temp);
}