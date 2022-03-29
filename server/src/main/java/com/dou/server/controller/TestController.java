package com.dou.server.controller;

import com.dou.server.tag.BaseController;
import com.dou.server.exception.MyServerException;
import com.dou.server.tag.PassToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjd
 * @date 2021-11-01
 */
@Api(tags = "用户信息管理")
@RestController
public class TestController implements BaseController {

    @PassToken
    @GetMapping("")
    @ApiOperation(value = "1236", notes = "123")
    public void test() throws MyServerException {
        throw new MyServerException("1234445");
    }
}
