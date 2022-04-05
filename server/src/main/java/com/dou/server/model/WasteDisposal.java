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

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="t_waste_disposal")
@Accessors(chain = true)
public class WasteDisposal extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("创建日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("物品名称")
    private String itemName;

    @ApiModelProperty("物品数量")
    private String itemAmount;

    @ApiModelProperty("存放地方")
    private String savePlace;

    @ApiModelProperty("是否存放48小时")
    private String isSave48hour;

    @ApiModelProperty("备注")
    private String remark;

    @Transient
    @ApiModelProperty("录入人")
    private String createUserName;
}