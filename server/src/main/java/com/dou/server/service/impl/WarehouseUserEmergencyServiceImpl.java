package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseUserEmergencyMapper;
import com.dou.server.model.WarehouseUserEmergency;
import com.dou.server.service.WarehouseUserEmergencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseUserEmergencyServiceImpl extends BaseServiceImpl<WarehouseUserEmergency> implements WarehouseUserEmergencyService {

    private final WarehouseUserEmergencyMapper warehouseUserEmergencyMapper;

    @Override
    public List<WarehouseUserEmergency> getList(WarehouseUserEmergency temp) {
        return warehouseUserEmergencyMapper.getList(temp);
    }
}