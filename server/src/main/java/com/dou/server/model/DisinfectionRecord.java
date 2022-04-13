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
@Table(name="t_disinfection_record")
@Accessors(chain = true)
public class DisinfectionRecord extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("监管仓")
    private Integer warehouse;

    @ApiModelProperty("消杀区域")
    private String area;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty("消杀时间")
    private Date date;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("消毒液配比")
    private String disinfectantRatio;

    @Transient
    @ApiModelProperty("监管仓名称")
    private String warehouseName;

}