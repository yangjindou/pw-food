package com.dou.server.service;

import com.dou.server.model.WarehouseUser;

import java.util.Collection;
import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserService extends BaseService<WarehouseUser> {

    List<WarehouseUser> getList(WarehouseUser temp);

    void delete(Collection<?> ids);
}