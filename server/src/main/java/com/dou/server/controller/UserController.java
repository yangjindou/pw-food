package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.Pagination;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.utils.CommonUtils;
import io.swagger.annotations.Api;
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

    @GetMapping("")
    public ResponseEntity<?> get(User user) {
        return ResponseEntity.ok(userService.get(user));
    }

    @GetMapping("list")
    public ResponseEntity<?> getList(Pagination pagination, User user) {
        return ResponseEntity.ok(userService.getPage(pagination, user));
    }

    @PostMapping("")
    public ResponseEntity<?> add(User user) throws Exception {
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
    public ResponseEntity<?> delete(String ids) throws LogicException {
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException("缺少参数");
        }
        userService.delete(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok().build();
    }
}
