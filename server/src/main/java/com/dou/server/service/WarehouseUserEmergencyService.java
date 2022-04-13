package com.dou.server.service;

import com.dou.server.model.WarehouseUserEmergency;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserEmergencyService extends BaseService<WarehouseUserEmergency> {

    List<WarehouseUserEmergency> getList(WarehouseUserEmergency temp);
}