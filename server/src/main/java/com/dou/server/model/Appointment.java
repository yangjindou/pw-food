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
    private String order;

    @ApiModelProperty("上报的省（省市区）")
    private String area;

    @ApiModelProperty("预约监管仓")
    private String warehouse;

    @ApiModelProperty("入仓时间")
    private Date warehousingDate;

    @ApiModelProperty("货物类别")
    private String goodType;

    @ApiModelProperty("货物名称")
    private Integer goodName;

    @ApiModelProperty("货物来源")
    private String goodSource;

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
    private Date backupsDate;

    @ApiModelProperty("备案状态")
    private String backupsState;
}