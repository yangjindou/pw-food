package com.dou.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="t_supervision_warehouse")
@Accessors(chain = true)
public class SupervisionWarehouse extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("国家平台备案号")
    private String recordNumber;

    @ApiModelProperty("监管仓编码")
    private String code;

    @ApiModelProperty("监管仓编码")
    private String name;

    @ApiModelProperty("集中监管仓分类")
    private String type;

    @ApiModelProperty("建筑面积（平方米）")
    private String s;

    @ApiModelProperty("容量（千克）")
    private String v;

    @ApiModelProperty("监管仓所在省（市、区）")
    private String area;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("启用日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date enableDate;

    @ApiModelProperty("运营企业名称")
    private String enterpriseName;

    @ApiModelProperty("运营企业统一社会信用代码")
    private String enterpriseCode;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("集中监管仓经度")
    private String longitude;

    @ApiModelProperty("集中监管仓纬度")
    private String latitude;

    @ApiModelProperty("状态（正常、闭仓、废弃）")
    private String state;

}