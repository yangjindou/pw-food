package com.dou.server.service.impl;

import com.dou.server.mapper.WarehouseUserCheckMapper;
import com.dou.server.mapper.WarehouseUserEmergencyMapper;
import com.dou.server.mapper.WarehouseUserIsolateMapper;
import com.dou.server.mapper.WarehouseUserMapper;
import com.dou.server.model.WarehouseUser;
import com.dou.server.model.WarehouseUserCheck;
import com.dou.server.model.WarehouseUserEmergency;
import com.dou.server.model.WarehouseUserIsolate;
import com.dou.server.service.WarehouseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class WarehouseUserServiceImpl extends BaseServiceImpl<WarehouseUser> implements WarehouseUserService {

    private final WarehouseUserMapper warehouseUserMapper;
    private final WarehouseUserCheckMapper warehouseUserCheckMapper;
    private final WarehouseUserEmergencyMapper warehouseUserEmergencyMapper;
    private final WarehouseUserIsolateMapper warehouseUserIsolateMapper;

    @Override
    public List<WarehouseUser> getList(WarehouseUser temp) {
        return warehouseUserMapper.getList(temp);
    }

    @Override
    public void delete(Collection<?> ids) {
        super.delete(ids);
        ids.forEach(id -> {
            warehouseUserCheckMapper.delete(new WarehouseUserCheck().setPid(Integer.parseInt(id.toString())));
            warehouseUserEmergencyMapper.delete(new WarehouseUserEmergency().setPid(Integer.parseInt(id.toString())));
            warehouseUserIsolateMapper.delete(new WarehouseUserIsolate().setPid(Integer.parseInt(id.toString())));
        });
    }
}