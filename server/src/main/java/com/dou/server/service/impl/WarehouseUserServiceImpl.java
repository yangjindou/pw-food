package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseUserMapper;
import com.dou.server.model.WarehouseUser;
import com.dou.server.service.WarehouseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseUserServiceImpl extends BaseServiceImpl<WarehouseUser> implements WarehouseUserService {

    private final WarehouseUserMapper warehouseUserMapper;

    @Override
    public List<WarehouseUser> getList(WarehouseUser temp) {
        return warehouseUserMapper.getList(temp);
    }
}