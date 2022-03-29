package com.dou.server.exception;

/**
 * 登录异常
 * @author yangjd
 * @date 2022-03-29
 */
public class AuthException extends Exception {

    public AuthException() {
        super("鉴权异常");
    }

    public AuthException(String message) {
        super(message);
    }
}