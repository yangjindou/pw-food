package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="sys_file")
@Accessors(chain = true)
public class SysFile extends BaseEntity implements Serializable {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("真实名称")
    private String realName;

    @ApiModelProperty("地址")
    private String path;
}