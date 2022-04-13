package com.dou.server.mapper;

import com.dou.server.model.WarehouseUserEmergency;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserEmergencyMapper extends MyMapper<WarehouseUserEmergency> {

    List<WarehouseUserEmergency> getList(WarehouseUserEmergency temp);
}