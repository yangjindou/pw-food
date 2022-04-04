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

    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Dict dict) {
        return ResponseEntity.ok(dictService.getPage(pagination, dict));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Dict dict) throws Exception {
        if (CommonUtils.varIsBlank(dict.getName(), dict.getSign())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.add(dict);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Dict dict) throws Exception {
        if (CommonUtils.varIsBlank(dict.getId(), dict.getSign())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.update(dict);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) throws Exception {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        dictService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }

    @PassToken
    @GetMapping("/data/sign/{sign}")
    public ResponseEntity<?> getDataListByCode(@ApiParam("标识") @PathVariable String sign) throws LogicException {
        if (CommonUtils.varIsBlank(sign)) throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        return ResponseEntity.ok(dictDataService.getListByCode(sign));
    }

    @GetMapping("/data/list")
    public ResponseEntity<?> getDataList(Pagination pagination, DictData dictData) {
        return ResponseEntity.ok(dictDataService.getPage(pagination, dictData));
    }

    @PostMapping("/data")
    public ResponseEntity<?> dataAdd(@RequestBody List<DictData> dictDatas) throws Exception {
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
