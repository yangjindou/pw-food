package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Appointment;
import com.dou.server.model.Pagination;
import com.dou.server.service.AppointmentService;
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
@RequestMapping("/appointment")
@RequiredArgsConstructor
@RestController
@Api(tags = "预约管理接口")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @ApiOperation(value = "预约列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Appointment appointment) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(appointmentService.getList(appointment)));
        } else {
            return ResponseEntity.ok(appointmentService.getList(appointment));
        }
    }

    @ApiOperation(value = "预约新增", notes = "监管仓必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Appointment appointment) {
        if (CommonUtils.varIsBlank(appointment.getWarehouse())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        appointmentService.add(appointment);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "预约修改", notes = "id、监管仓必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) {
        if (CommonUtils.varIsBlank(appointment.getId(), appointment.getWarehouse())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        appointmentService.update(appointment);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "预约删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        appointmentService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}