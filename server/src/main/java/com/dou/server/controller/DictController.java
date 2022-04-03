package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Dict;
import com.dou.server.model.DictData;
import com.dou.server.model.Pagination;
import com.dou.server.service.DictDataService;
import com.dou.server.service.DictService;
import com.dou.server.utils.CommonUtils;
import io.swagger.annotations.Api;
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
            throw new LogicException("缺少参数");
        }
        dictService.add(dict);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Dict dict) throws Exception {
        if (CommonUtils.varIsBlank(dict.getId())) {
            throw new LogicException("缺少参数");
        }
        dictService.update(dict);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) throws LogicException {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException("缺少参数");
        }
        dictService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/data/list")
    public ResponseEntity<?> getDataList(Pagination pagination, DictData dictData) {
        return ResponseEntity.ok(dictDataService.getPage(pagination, dictData));
    }

    @PostMapping("/data")
    public ResponseEntity<?> dataAdd(@RequestBody List<DictData> dictDatas) throws Exception {
        for (DictData dictData : dictDatas) {
            if (CommonUtils.varIsBlank(dictData.getPid(), dictData.getName(), dictData.getValue())) {
                throw new LogicException("缺少参数");
            }
        }
        dictDataService.add(dictDatas);
        return ResponseEntity.ok().build();
    }
}
