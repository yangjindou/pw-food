package com.dou.server.model;

import com.dou.server.tag.ResultEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回的实体类
 * @author yangjd
 */
@Data
@AllArgsConstructor
public class ResultEntity implements Serializable {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("消息")
    private String  msg;

    @ApiModelProperty("数量")
    private Integer count;

    @ApiModelProperty("数据")
    private Object  data;

    /**
     * 传状态码和消息
     * @param code 状态码
     * @param message 消息
     */
    public ResultEntity(Integer code, String message) {
        this(code,message,null,null);
    }

    /**
     * 传封装好的状态码和消息
     * @param hsCode HttpStatusCode枚举类
     */
    public ResultEntity(ResultEnums hsCode) {
        this(hsCode,null);
    }

    /**
     * 传封装好的状态码和消息、数据
     * @param hsCode HttpStatusCode枚举类
     * @param data 数据
     */
    public ResultEntity(ResultEnums hsCode, Object data) {
        this(hsCode.getCode(),hsCode.getMessage(),null,data);
    }

    /**
     * 传状态码和消息、数据
     * @param code 状态码
     * @param message 消息
     * @param data 数据
     */
    public ResultEntity(Integer code, String message, Object data) {
        this(code,message,null,data);
    }
}