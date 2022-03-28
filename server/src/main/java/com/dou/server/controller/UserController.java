package com.dou.server.controller;

import com.dou.server.model.ResultEntity;
import com.dou.server.model.User;
import com.dou.server.service.UserService;
import com.dou.server.tag.BaseController;
import com.dou.server.tag.PassToken;
import com.dou.server.tag.ResultEnums;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yangjd
 */
@RequestMapping("/user")
@RestController
public class UserController implements BaseController {

    @Resource
    private UserService userService;
    @Resource
    RedisUtils redisUtils;

    @GetMapping("")
    public ResultEntity get(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit, User user) {
        return setPage(userService.get(page,limit,user));
    }

    @PostMapping("")
    public ResultEntity add(User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getName())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(userService.add(user));
    }

    @PutMapping("")
    public ResultEntity update(@RequestBody User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getId())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(userService.update(user));
    }

    @DeleteMapping("")
    @SuppressWarnings("unchecked")
    public ResultEntity delete(@RequestBody Map<String, Object> params) {
        List<Integer> ids = (List<Integer>) params.get("ids");
        if (CommonUtils.varIsBlank(ids)) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(userService.delete(ids));
    }

    @PassToken
    @PostMapping("/login")
    public ResultEntity login(User user) throws Exception {
        if (CommonUtils.varIsBlank(user.getName(),user.getPassword())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return userService.verifyUser(user);
    }

    @GetMapping("/verifyLogin")
    public ResultEntity verifyLogin() {
        User requestUser = User.getRequestUser();
        assert requestUser != null;
        requestUser.protectInfo();
        return CommonUtils.varIsBlank(requestUser) ? error() : success(requestUser);
    }

    @GetMapping("/logout")
    public ResultEntity logout(){
        User requestUser = User.getRequestUser();
        assert requestUser != null;
        redisUtils.delete(RedisUtils.USER_PREFIX + requestUser.getId());
        return success();
    }

    @PassToken
    @PostMapping("register")
    public ResultEntity register(User user) throws Exception {
        if (StringUtils.isAnyBlank(user.getName(),user.getPassword())) {
            return error(ResultEnums.E_PARAM_MISS);
        }
        return setResult(userService.add(user));
    }

    @PostMapping("passwordModify")
    public ResultEntity passwordModify(String oldPwd, String newPwd) throws Exception {
        if (CommonUtils.varIsBlank(oldPwd,newPwd)) {
            return new ResultEntity(ResultEnums.E_PARAM_MISS);
        }
        return setResult(userService.passwordModify(oldPwd,newPwd));
    }
}
