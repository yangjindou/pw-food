package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.tag.PassToken;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangjindou
 * @date 2022-03-30
 */
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final RedisUtils redisUtils;

    @PassToken
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getLoginName(), user.getPassword())) {
            throw new LogicException("缺少参数");
        }
        return ResponseEntity.ok(userService.verifyUser(user));
    }

    @GetMapping("/verifyLogin")
    public ResponseEntity<?> verifyLogin() {
        User requestUser = User.getRequestUser();
        requestUser.protectInfo();
        return ResponseEntity.ok(requestUser);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        User requestUser = User.getRequestUser();
        redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        return ResponseEntity.ok().build();
    }

    @PassToken
    @PostMapping("register")
    public ResponseEntity<?> register(User user) throws Exception {
        if (StringUtils.isAnyBlank(user.getLoginName(), user.getPassword())) {
            throw new LogicException("缺少参数");
        }
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("passwordModify")
    public ResponseEntity<?> passwordModify(String oldPwd, String newPwd) throws Exception {
        if (CommonUtils.varIsBlank(oldPwd,newPwd)) {
            throw new LogicException("缺少参数");
        }
        userService.passwordModify(oldPwd,newPwd);
        return ResponseEntity.ok().build();
    }
}
