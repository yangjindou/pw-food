package com.dou.server.mapper;

import com.dou.server.model.Device;
import com.dou.server.tag.MyMapper;

import java.util.List;

/**
 * @author yangjd
 */
public interface DeviceMapper extends MyMapper<Device> {

    List<Device> getList(Device temp);
}