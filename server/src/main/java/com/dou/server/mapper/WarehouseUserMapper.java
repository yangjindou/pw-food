package com.dou.server.mapper;

import com.dou.server.model.WarehouseUser;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface WarehouseUserMapper extends MyMapper<WarehouseUser> {

    List<WarehouseUser> getList(WarehouseUser temp);
}