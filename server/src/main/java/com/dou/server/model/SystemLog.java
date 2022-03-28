package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志实体类
 * @author yangjd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemLog implements Serializable {

    @ApiModelProperty("映射地址")
    private String mapping;

    @ApiModelProperty("方法名")
    private String method;

    @ApiModelProperty("参数")
    private String params;
}