package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.WarehouseUser;
import com.dou.server.service.WarehouseUserService;
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
@RequestMapping("/warehouseUser")
@RequiredArgsConstructor
@RestController
@Api(tags = "监管仓用户管理接口")
public class WarehouseUserController {

    private final WarehouseUserService warehouseUserService;

    @ApiOperation(value = "监管仓用户列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, WarehouseUser warehouseUser) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(warehouseUserService.getList(warehouseUser)));
        } else {
            return ResponseEntity.ok(warehouseUserService.getList(warehouseUser));
        }
    }

    @ApiOperation(value = "监管仓用户新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody WarehouseUser warehouseUser) {
        if (CommonUtils.varIsBlank(warehouseUser.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseUserService.add(warehouseUser);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓用户修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody WarehouseUser warehouseUser) {
        if (CommonUtils.varIsBlank(warehouseUser.getId(), warehouseUser.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseUserService.update(warehouseUser);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "监管仓用户删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        warehouseUserService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
