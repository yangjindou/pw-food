package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.DisinfectionRecord;
import com.dou.server.model.Pagination;
import com.dou.server.service.DisinfectionRecordService;
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
@RequestMapping("/disinfectionRecord")
@RequiredArgsConstructor
@RestController
@Api(tags = "环境消杀记录管理接口")
public class DisinfectionRecordController {

    private final DisinfectionRecordService disinfectionRecordService;

    @ApiOperation(value = "环境消杀记录列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, DisinfectionRecord disinfectionRecord) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(disinfectionRecordService.getList(disinfectionRecord)));
        } else {
            return ResponseEntity.ok(disinfectionRecordService.getList(disinfectionRecord));
        }
    }

    @ApiOperation(value = "环境消杀记录新增", notes = "监管仓必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody DisinfectionRecord disinfectionRecord) {
        if (CommonUtils.varIsBlank(disinfectionRecord.getSupervisionWarehouse())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectionRecordService.add(disinfectionRecord);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "环境消杀记录修改", notes = "id、监管仓必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody DisinfectionRecord disinfectionRecord) {
        if (CommonUtils.varIsBlank(disinfectionRecord.getId(), disinfectionRecord.getSupervisionWarehouse())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectionRecordService.update(disinfectionRecord);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "环境消杀记录删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectionRecordService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }

    @PassToken
    @ApiOperation(value = "环境消杀记录导出", notes = "")
    @GetMapping("/export")
    public ResponseEntity<?> exportList(DisinfectionRecord disinfectionRecord) throws IOException {
        ByteArrayOutputStream os = disinfectionRecordService.export(disinfectionRecord);
        String fileName = String.format("环境消杀记录%s.xls", DateUtils.dateToFormatStr(new Date(),"yyyyMMddHHmmss"));
        return new ResponseEntity<>(os.toByteArray(), HttpContextUtils.excelHeaders(fileName), HttpStatus.OK);
    }
}
