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
    private Integer filingOrder;

    @ApiModelProperty("上报的省（省市区）")
    private String area;

    @ApiModelProperty("预约监管仓")
    private Integer warehouse;

    @ApiModelProperty("货物类别")
    private Integer goodType;

    @ApiModelProperty("货物名称")
    private String goodName;

    @ApiModelProperty("货物来源")
    private Integer goodSource;

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

    @ApiModelProperty("备案状态")
    private String filingState;

    @ApiModelProperty("申请修改次数")
    private Integer applyUpdateCount;

    @ApiModelProperty("应急")
    private Boolean emergency;

    @ApiModelProperty("采样货物名称")
    private String samplingGoodName;

    @ApiModelProperty("采样数量")
    private Integer samplingAmount;

    @ApiModelProperty("采样时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date samplingDate;

    @ApiModelProperty("采样备注")
    private String samplingRemark;

    @ApiModelProperty("采样检测人")
    private String samplingUser;

    @ApiModelProperty("卸货人员")
    private String uninstallUser;

    @ApiModelProperty("卸货时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date uninstallDate;

    @ApiModelProperty("入仓时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date warehousingDate;

    @ApiModelProperty("入仓禽类（Kg）")
    private Double warehousingWeightPoultry;

    @ApiModelProperty("入仓畜类（Kg）")
    private Double warehousingWeightLivestock;

    @ApiModelProperty("入仓水产品（Kg）")
    private Double warehousingWeightAquatic;

    @ApiModelProperty("入仓其他（Kg）")
    private Double warehousingWeightOther;

    @ApiModelProperty("出仓时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Double warehousedDate;

    @ApiModelProperty("出仓禽类（Kg）")
    private Double warehousedWeightPoultry;

    @ApiModelProperty("出仓禽类（Kg）")
    private Double warehousedWeightLivestock;

    @ApiModelProperty("出仓水产品（Kg）")
    private Double warehousedWeightAquatic;

    @ApiModelProperty("出仓其他（Kg）")
    private Double warehousedWeightOther;

    @ApiModelProperty("出仓消杀证明图片")
    private String warehousedDisinfectionCertificate;

    @ApiModelProperty("出仓核酸检测图片")
    private String warehousedInspectionCertificate;
}