package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.utils.CommonUtils;
import io.swagger.annotations.Api;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@ApiParam("用户id") @PathVariable Integer id) throws Exception {
        if (CommonUtils.varIsBlank(id)) {
            throw new LogicException("缺少参数");
        }
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, User user) {
        return ResponseEntity.ok(userService.getPage(pagination, user));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getLoginName())) {
            throw new LogicException("缺少参数");
        }
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getId())) {
            throw new LogicException("缺少参数");
        }
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(String ids) throws Exception {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException("缺少参数");
        }
        userService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
