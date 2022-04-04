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
@Table(name="t_device")
@Accessors(chain = true)
public class Device extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("检验计量器")
    private String inspectionMeter;

    @ApiModelProperty("是否定期校验")
    private String isPeriodicInspection;

    @ApiModelProperty("校验日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date inspectionDate;

    @ApiModelProperty("检测废弃物是否按规定存放")
    private String isRequiredSave;

    @Transient
    @ApiModelProperty("录入人")
    private String createUserName;
}