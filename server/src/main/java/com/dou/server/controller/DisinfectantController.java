package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Disinfectant;
import com.dou.server.model.Pagination;
import com.dou.server.service.DisinfectantService;
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
@RequestMapping("/disinfectant")
@RequiredArgsConstructor
@RestController
@Api(tags = "消毒液管理接口")
public class DisinfectantController {

    private final DisinfectantService disinfectantService;

    @ApiOperation(value = "消毒液列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Disinfectant disinfectant) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(disinfectantService.getList(disinfectant)));
        } else {
            return ResponseEntity.ok(disinfectantService.getList(disinfectant));
        }
    }

    @ApiOperation(value = "消毒液新增", notes = "名称必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Disinfectant disinfectant) {
        if (CommonUtils.varIsBlank(disinfectant.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectantService.add(disinfectant);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "消毒液修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Disinfectant disinfectant) {
        if (CommonUtils.varIsBlank(disinfectant.getId(), disinfectant.getName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectantService.update(disinfectant);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "消毒液删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        disinfectantService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
