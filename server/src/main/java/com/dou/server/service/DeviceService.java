package com.dou.server.service;

import com.dou.server.model.Device;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author yangjd
 */
public interface DeviceService extends BaseService<Device> {

    List<Device> getList(Device temp);

    ByteArrayOutputStream export(Device temp) throws IOException;
}