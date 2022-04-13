package com.dou.server.mapper;

import com.dou.server.model.Warehouse;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseMapper extends MyMapper<Warehouse> {

    List<Warehouse> getList(Warehouse temp);
}