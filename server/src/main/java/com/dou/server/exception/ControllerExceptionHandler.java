package com.dou.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    // token等鉴权异常
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authExceptionHandler(AuthException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
    }

    // 业务逻辑异常
    @ExceptionHandler(LogicException.class)
    public ResponseEntity<?> logicExceptionHandler(LogicException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    // redis连接异常
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<?> redisConnectionFailureExceptionHandler(RedisConnectionFailureException e) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // 其他异常处理
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        Map<String,Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}