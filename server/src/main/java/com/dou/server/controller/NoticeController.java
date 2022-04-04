package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Notice;
import com.dou.server.model.Pagination;
import com.dou.server.service.NoticeService;
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
@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
@Api(tags = "公告管理接口")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "公告列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, Notice notice) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(noticeService.getList(notice)));
        } else {
            return ResponseEntity.ok(noticeService.getList(notice));
        }
    }

    @ApiOperation(value = "公告新增", notes = "名称、标识必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody Notice notice) {
        if (CommonUtils.varIsBlank(notice.getTitle(), notice.getContent())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        noticeService.add(notice);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "公告修改", notes = "id、标识必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Notice notice) {
        if (CommonUtils.varIsBlank(notice.getId(), notice.getTitle(), notice.getContent())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        noticeService.update(notice);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "公告删除", notes = "ids必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        noticeService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
