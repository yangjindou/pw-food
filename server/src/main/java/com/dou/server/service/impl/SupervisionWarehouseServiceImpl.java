package com.dou.server.service.impl;

import com.dou.server.mapper.SupervisionWarehouseMapper;
import com.dou.server.model.SupervisionWarehouse;
import com.dou.server.service.SupervisionWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangjd
 */
@Service
@RequiredArgsConstructor
public class SupervisionWarehouseServiceImpl extends BaseServiceImpl<SupervisionWarehouse> implements SupervisionWarehouseService {

    private final SupervisionWarehouseMapper supervisionWarehouseMapper;

}