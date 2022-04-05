package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.SupervisionWarehouse;
import com.dou.server.service.SupervisionWarehouseService;
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
@RequestMapping("/supervisionWarehouse")
@RequiredArgsConstructor
@RestController
@Api(tags = "监管仓管理接口")
public class SupervisionWarehouseController {

    private final SupervisionWarehouseService supervisionWarehouseService;

    @ApiOperation(value = "监管仓列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, SupervisionWarehouse supervisionWarehouse) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(supervisionWarehouseService.getList(supervisionWarehouse)));
        } else {
            return ResponseEntity.ok(supervisionWarehouseService.getList(supervisionWarehouse));
        }
    }

    @ApiOperation(value = "监管仓新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody SupervisionWarehouse supervisionWarehouse) {
        if (CommonUtils.varIsBlank(supervisionWarehouse.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        supervisionWarehouseService.add(supervisionWarehouse);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody SupervisionWarehouse supervisionWarehouse) {
        if (CommonUtils.varIsBlank(supervisionWarehouse.getId(), supervisionWarehouse.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        supervisionWarehouseService.update(supervisionWarehouse);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        supervisionWarehouseService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
