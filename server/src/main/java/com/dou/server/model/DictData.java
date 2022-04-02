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
@Table(name="sys_dict_data")
@Accessors(chain = true)
public class DictData extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("dict_id")
    private Integer pid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("排序")
    private String sort;
}