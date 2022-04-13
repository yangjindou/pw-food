package com.dou.server.mapper;

import com.dou.server.model.WarehouseUserCheck;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserCheckMapper extends MyMapper<WarehouseUserCheck> {

    List<WarehouseUserCheck> getList(WarehouseUserCheck temp);
}