package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseMapper;
import com.dou.server.model.Warehouse;
import com.dou.server.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl extends BaseServiceImpl<Warehouse> implements WarehouseService {

    private final WarehouseMapper warehouseMapper;

    @Override
    public List<Warehouse> getList(Warehouse temp) {
        return warehouseMapper.getList(temp);
    }
}