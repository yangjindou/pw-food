package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Device;
import com.dou.server.model.Pagination;
import com.dou.server.service.DeviceService;
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
@RequestMapping("/device")
@RequiredArgsConstructor
@RestController
@Api(tags = "设备管理接口")
public class DeviceController {

    private final DeviceService deviceService;

    @ApiOperation(value = "设备列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Device device) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(deviceService.getList(device)));
        } else {
            return ResponseEntity.ok(deviceService.getList(device));
        }
    }

    @ApiOperation(value = "设备新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Device device) {
        deviceService.add(device);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "设备修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Device device) {
        if (CommonUtils.varIsBlank(device.getId())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        deviceService.update(device);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "设备删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        deviceService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
