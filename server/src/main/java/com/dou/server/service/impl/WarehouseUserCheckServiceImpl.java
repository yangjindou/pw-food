package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseUserCheckMapper;
import com.dou.server.model.WarehouseUserCheck;
import com.dou.server.service.WarehouseUserCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseUserCheckServiceImpl extends BaseServiceImpl<WarehouseUserCheck> implements WarehouseUserCheckService {

    private final WarehouseUserCheckMapper warehouseUserCheckMapper;

    @Override
    public List<WarehouseUserCheck> getList(WarehouseUserCheck temp) {
        return warehouseUserCheckMapper.getList(temp);
    }
}