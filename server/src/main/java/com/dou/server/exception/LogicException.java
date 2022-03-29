package com.dou.server.exception;

/**
 * 登录异常
 * @author yangjd
 * @date 2022-03-29
 */
public class LogicException extends Exception {

    public LogicException() {
        super("业务错误");
    }

    public LogicException(String message) {
        super(message);
    }
}