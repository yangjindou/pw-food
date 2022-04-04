package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.tag.Constant;
import com.dou.server.utils.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author yangjd
 */
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@Api(tags = "用户接口")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "通过id查询用户信息", notes = "id必填")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@ApiParam("用户id") @PathVariable Integer id) {
        if (CommonUtils.varIsBlank(id)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        return ResponseEntity.ok(userService.getById(id));
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, User user) {
        if (CommonUtils.varIsNotBlank(pagination.getPageNum(), pagination.getPageSize())) {
            PageHelper.startPage(pagination.getPageNum(), pagination.getPageSize());
            return ResponseEntity.ok(new PageInfo<>(userService.getList(user)));
        } else {
            return ResponseEntity.ok(userService.getList(user));
        }
    }

    @ApiOperation(value = "用户新增", notes = "账号、用户名必填")
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody User user) {
        if (CommonUtils.varIsBlank(user.getLoginName(), user.getUserName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "用户修改", notes = "id必填")
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user) {
        if (CommonUtils.varIsBlank(user.getId())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "用户删除", notes = "id必填")
    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        userService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
