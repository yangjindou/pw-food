package com.dou.server.config;

import com.dou.server.model.ResultEntity;
import com.dou.server.tag.ResultEnums;
import com.dou.server.tag.MyServerException;
import com.dou.server.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller层统一异常处理
 * @author yangjd
 * @date 2021-10-28
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultEntity handler(Exception e) {
        // 如果是MyServerException自定义异常，就打印出异常信息内容
        if (e instanceof MyServerException) {
            if (CommonUtils.varIsBlank(e.getMessage())) {
                return new ResultEntity(((MyServerException)e).getResult());
            } else {
                return new ResultEntity(ResultEnums.CODE_ERROR,e.getMessage());
            }
        } else {
            log.error("系统异常！");
            log.error("",e);
            return new ResultEntity(ResultEnums.E_EXCEPTION);
        }
    }
}