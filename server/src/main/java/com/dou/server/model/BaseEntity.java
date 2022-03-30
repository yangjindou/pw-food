package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author yangjindou
 * @date 2022-03-30
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseEntity {

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("创建人")
    private Integer createUser;

    @ApiModelProperty("修改日期")
    private Date updateDate;

    @ApiModelProperty("修改人")
    private Integer updateUser;
}
