package com.dou.server.tag;

/**
 * 服务（当前系统业务）异常
 * @author yangjd
 * @date 2021-06-01
 */
public class MyServerException extends Exception {

    private static final long serialVersionUID = 1L;

    private ResultEnums result;

    public MyServerException(String message) {
        super(message);
    }

    public MyServerException(ResultEnums result) {
        this.result = result;
    }

    public ResultEnums getResult() {
        return result;
    }
}