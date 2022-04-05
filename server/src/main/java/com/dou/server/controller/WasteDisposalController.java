package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.WasteDisposal;
import com.dou.server.service.WasteDisposalService;
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
@RequestMapping("/wasteDisposal")
@RequiredArgsConstructor
@RestController
@Api(tags = "废物处理管理接口")
public class WasteDisposalController {

    private final WasteDisposalService wasteDisposalService;

    @ApiOperation(value = "废物处理列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, WasteDisposal wasteDisposal) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(wasteDisposalService.getList(wasteDisposal)));
        } else {
            return ResponseEntity.ok(wasteDisposalService.getList(wasteDisposal));
        }
    }

    @ApiOperation(value = "废物处理新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody WasteDisposal wasteDisposal) {
        if (CommonUtils.varIsBlank(wasteDisposal.getItemName(), wasteDisposal.getItemAmount())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        wasteDisposalService.add(wasteDisposal);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "废物处理修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody WasteDisposal wasteDisposal) {
        if (CommonUtils.varIsBlank(wasteDisposal.getId(), wasteDisposal.getItemName(), wasteDisposal.getItemAmount())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        wasteDisposalService.update(wasteDisposal);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "废物处理删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        wasteDisposalService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
