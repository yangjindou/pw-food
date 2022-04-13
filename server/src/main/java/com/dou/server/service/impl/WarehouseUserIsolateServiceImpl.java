package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseUserIsolateMapper;
import com.dou.server.model.WarehouseUserIsolate;
import com.dou.server.service.WarehouseUserIsolateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseUserIsolateServiceImpl extends BaseServiceImpl<WarehouseUserIsolate> implements WarehouseUserIsolateService {

    private final WarehouseUserIsolateMapper warehouseUserIsolateMapper;

    @Override
    public List<WarehouseUserIsolate> getList(WarehouseUserIsolate temp) {
        return warehouseUserIsolateMapper.getList(temp);
    }
}