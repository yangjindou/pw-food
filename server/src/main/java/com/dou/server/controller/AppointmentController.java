package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Appointment;
import com.dou.server.model.Pagination;
import com.dou.server.model.vo.AppointmentVO;
import com.dou.server.service.AppointmentService;
import com.dou.server.tag.Constant;
import com.dou.server.tag.PassToken;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.DateUtils;
import com.dou.server.utils.HttpContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

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
    public ResponseEntity<?> getList(Pagination pagination, AppointmentVO appointment) {
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

    @ApiOperation(value = "预约修改", notes = "id必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) {
        if (CommonUtils.varIsBlank(appointment.getId())) {
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

    @PassToken
    @ApiOperation(value = "预约导出", notes = "导出excel")
    @GetMapping("/export")
    public ResponseEntity<?> exportList(AppointmentVO appointment) throws IOException {
        ByteArrayOutputStream os = appointmentService.export(appointment);
        String fileName = String.format("预约信息%s.xls", DateUtils.dateToFormatStr(new Date(),"yyyyMMddHHmmss"));
        return new ResponseEntity<>(os.toByteArray(), HttpContextUtils.excelHeaders(fileName), HttpStatus.OK);
    }

    @PassToken
    @ApiOperation(value = "出入仓导出", notes = "")
    @GetMapping("/warehouse/export")
    public ResponseEntity<?> exportWarehouseList(AppointmentVO appointment) throws IOException {
        if (CommonUtils.varIsBlank(appointment.getFilterType())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        ByteArrayOutputStream os = appointmentService.exportWarehouse(appointment);
        String fileName = String.format("%s信息%s.xls", appointment.getFilterType(), DateUtils.dateToFormatStr(new Date(),"yyyyMMddHHmmss"));
        return new ResponseEntity<>(os.toByteArray(), HttpContextUtils.excelHeaders(fileName), HttpStatus.OK);
    }
}
