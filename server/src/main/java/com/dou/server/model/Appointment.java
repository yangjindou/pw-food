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
@Table(name="t_appointment")
@Accessors(chain = true)
public class Appointment extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("备案单号")
    private String filingOrder;

    @ApiModelProperty("上报的省（省市区）")
    private String area;

    @ApiModelProperty("预约监管仓")
    private Integer warehouse;

    @Transient
    @ApiModelProperty("预约监管仓名称")
    private String warehouseName;

    @ApiModelProperty("入仓时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date warehousingDate;

    @ApiModelProperty("入仓开始时间")
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date warehousingDateStart;

    @ApiModelProperty("入仓结束时间")
    @Transient
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date warehousingDateEnd;

    @ApiModelProperty("货物类别")
    private Integer goodType;

    @Transient
    @ApiModelProperty("货物类别名称")
    private String goodTypeName;

    @ApiModelProperty("货物名称")
    private Integer goodName;

    @ApiModelProperty("货物来源")
    private Integer goodSource;

    @Transient
    @ApiModelProperty("货物来源名称")
    private String goodSourceName;

    @ApiModelProperty("来源名称")
    private String sourceName;

    @ApiModelProperty("出仓证明")
    private String warehousedProve;

    @ApiModelProperty("原产国/产地")
    private String originPlace;

    @ApiModelProperty("件数")
    private Integer amount;

    @ApiModelProperty("重量（Kg）")
    private Integer weight;

    @ApiModelProperty("检疫证明")
    private String quarantineCertificate;

    @ApiModelProperty("报关单")
    private String customsBill;

    @ApiModelProperty("港口核酸证明")
    private String portInspectionCertificate;

    @ApiModelProperty("港口消杀证明")
    private String portDisinfectionCertificate;

    @ApiModelProperty("承运司机")
    private String driver;

    @ApiModelProperty("车牌号")
    private String carNumber;

    @ApiModelProperty("司机电话")
    private String driverPhone;

    @ApiModelProperty("驳回理由")
    private String refuseReason;

    @ApiModelProperty("修改理由")
    private String updateReason;

    @ApiModelProperty("备案时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date filingDate;

    @ApiModelProperty("备案状态")
    private String filingState;
}