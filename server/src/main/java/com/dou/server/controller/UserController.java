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

import java.util.List;
import java.util.Map;

/**
 * @author yangjd
 */
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final RedisUtils redisUtils;

    @GetMapping("")
    public ResponseEntity<?> get(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit, User user) {
        return ResponseEntity.ok(userService.get(page,limit,user));
    }

    @PostMapping("")
    public ResponseEntity<?> add(User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getName())) {
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
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> delete(@RequestBody Map<String, Object> params) throws LogicException {
        List<Integer> ids = (List<Integer>) params.get("ids");
        if (CommonUtils.varIsBlank(ids)) {
            throw new LogicException("缺少参数");
        }
        userService.delete(ids);
        return ResponseEntity.ok().build();
    }

    @PassToken
    @PostMapping("/login")
    public ResponseEntity<?> login(User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getName(),user.getPassword())) {
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
        if (StringUtils.isAnyBlank(user.getName(),user.getPassword())) {
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
