package com.dou.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * controller层统一异常处理
 * @author yangjd
 * @date 2021-10-28
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authExceptionHandler(AuthException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> noHandlerFoundExceptionHandler(AuthException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("message", "找不到");
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}