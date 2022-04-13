package com.dou.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="t_warehouse_user")
@Accessors(chain = true)
public class WarehouseUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("工作种类")
    private String workType;

    @ApiModelProperty("所属监管仓")
    private Integer warehouse;

    @ApiModelProperty("是否接种疫苗")
    private String isInoculate;

    @ApiModelProperty("接种次数")
    private String inoculateCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty("接种时间")
    private Date inoculateDate;

    @Transient
    @ApiModelProperty("所属监管仓名称")
    private String warehouseName;
}