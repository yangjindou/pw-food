package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Dict;
import com.dou.server.model.DictData;
import com.dou.server.model.Pagination;
import com.dou.server.service.DictDataService;
import com.dou.server.service.DictService;
import com.dou.server.tag.Constant;
import com.dou.server.tag.PassToken;
import com.dou.server.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangjd
 */
@RequestMapping("/dict")
@RequiredArgsConstructor
@RestController
@Api(tags = "数据字典接口")
public class DictController {

    private final DictService dictService;
    private final DictDataService dictDataService;

    @ApiOperation(value = "数据字典列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Dict dict) {
        return ResponseEntity.ok(dictService.getPage(pagination, dict));
    }

    @ApiOperation(value = "数据字典新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Dict dict) {
        if (CommonUtils.varIsBlank(dict.getName(), dict.getSign())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.add(dict);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "数据字典修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Dict dict) {
        if (CommonUtils.varIsBlank(dict.getId(), dict.getName(), dict.getSign())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.update(dict);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "数据字典删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }

    @PassToken
    @ApiOperation(value = "通过标识获取数据字典数据", notes = "标识必填")
    @GetMapping("/data/sign/{sign}")
    public ResponseEntity<?> getDataListBysign(@ApiParam("标识") @PathVariable String sign) throws LogicException {
        if (CommonUtils.varIsBlank(sign)) throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        return ResponseEntity.ok(dictDataService.getListByCode(sign));
    }

    @ApiOperation(value = "获取数据字典数据列表", notes = "")
    @GetMapping("/data/list")
    public ResponseEntity<?> getDataList(Pagination pagination, DictData dictData) {
        return ResponseEntity.ok(dictDataService.getPage(pagination, dictData));
    }

    @ApiOperation(value = "数据字典修改或删除", notes = "pid、名称、值必填")
    @PostMapping("/data")
    public ResponseEntity<?> dataAddOrUpdate(@RequestBody List<DictData> dictDatas) {
        if (dictDatas.size() == 0) {
            throw new LogicException("没有数据");
        }
        for (DictData dictData : dictDatas) {
            if (CommonUtils.varIsBlank(dictData.getPid(), dictData.getName(), dictData.getValue())) {
                throw new LogicException(Constant.REQUEST_MISS_PARAMS);
            }
        }
        dictDataService.add(dictDatas);
        return ResponseEntity.ok().build();
    }
}
