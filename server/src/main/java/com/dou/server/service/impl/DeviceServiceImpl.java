package com.dou.server.service.impl;

import com.dou.server.mapper.DeviceMapper;
import com.dou.server.model.Device;
import com.dou.server.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

    private final DeviceMapper deviceMapper;

    @Override
    public List<Device> getList(Device temp) {
        return deviceMapper.getList(temp);
    }
}