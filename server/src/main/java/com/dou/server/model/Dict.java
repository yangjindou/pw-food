package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="sys_dict")
@Accessors(chain = true)
public class Dict extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("标识")
    private String sign;

    @ApiModelProperty("备注")
    private String remark;
}