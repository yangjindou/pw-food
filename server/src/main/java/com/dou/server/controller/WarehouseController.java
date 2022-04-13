package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.Warehouse;
import com.dou.server.service.WarehouseService;
import com.dou.server.tag.Constant;
import com.dou.server.utils.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author yangjd
 */
@RequestMapping("/warehouse")
@RequiredArgsConstructor
@RestController
@Api(tags = "监管仓管理接口")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @ApiOperation(value = "监管仓列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Warehouse warehouse) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(warehouseService.getList(warehouse)));
        } else {
            return ResponseEntity.ok(warehouseService.getList(warehouse));
        }
    }

    @ApiOperation(value = "监管仓新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Warehouse warehouse) {
        if (CommonUtils.varIsBlank(warehouse.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseService.add(warehouse);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Warehouse warehouse) {
        if (CommonUtils.varIsBlank(warehouse.getId(), warehouse.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseService.update(warehouse);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
