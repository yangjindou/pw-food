package com.dou.server.mapper;

import com.dou.server.model.WarehouseUserIsolate;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserIsolateMapper extends MyMapper<WarehouseUserIsolate> {

    List<WarehouseUserIsolate> getList(WarehouseUserIsolate temp);
}