package com.dou.server.tag;

/**
 * http状态码
 * @author yangjd
 * @date 2021-05-21
 */
public enum ResultEnums {

    /// <summary>
    /// HTTP状态码
    /// </summary>
    SUCCESS(10000, "请求成功"),

    ERROR(-10000, "请求失败"),
    E_PARAM_MISS(-10001, "缺少参数"),
    E_EXCEPTION(-10003, "异常信息错误"),
    E_USER_ERROR(-11000, "用户信息错误"),
    E_USER_MISS(-11001, "用户不存在"),
    E_USER_FAILURE(-11002, "用户已失效"),
    E_USER_PASSWORD_ERROR(-11003, "密码错误"),
    E_USER_OLD_PASSWORD_ERROR(-11004, "旧密码错误"),
    ERROR1(-19999, "失败");

    private final Integer code;
    private final String message;

    public final static Integer CODE_SUCCESS = 10000;
    public final static Integer CODE_PAGE = 0;
    public final static Integer CODE_ERROR = -10000;

    ResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}