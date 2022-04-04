package com.dou.server.controller;

import com.dou.server.exception.LogicException;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.tag.Constant;
import com.dou.server.tag.PassToken;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yangjindou
 * @date 2022-03-30
 */
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
@Api(tags = "系统授权接口")
public class AuthController {

    private final UserService userService;
    private final RedisUtils redisUtils;

    @ApiOperation(value = "登录", notes = "账号、密码必填")
    @PassToken
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (CommonUtils.varIsBlank(user.getLoginName(), user.getPassword())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        return ResponseEntity.ok(userService.verifyUser(user));
    }

    @ApiOperation(value = "退出", notes = "退出当前账号")
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(){
        User requestUser = User.getRequestUser();
        redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "验证是否登录", notes = "验证是否登录")
    @PostMapping("/verifyLogin")
    public ResponseEntity<?> verifyLogin() {
        User requestUser = User.getRequestUser();
        requestUser.protectInfo();
        return ResponseEntity.ok(requestUser);
    }

    @ApiOperation(value = "注册", notes = "账号、密码必填")
    @PassToken
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (CommonUtils.varIsBlank(user.getLoginName())) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        userService.register(user);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "修改密码", notes = "旧密码、新密码必填")
    @PutMapping("passwordModify")
    public ResponseEntity<?> passwordModify(@RequestBody Map<String, Object> params) {
        String oldPwd = params.get("oldPwd").toString();
        String newPwd = params.get("newPwd").toString();
        if (CommonUtils.varIsBlank(oldPwd,newPwd)) {
            throw new LogicException(Constant.REQUEST_MISS_PARAMS);
        }
        userService.passwordModify(oldPwd,newPwd);
        return ResponseEntity.ok().build();
    }
}
