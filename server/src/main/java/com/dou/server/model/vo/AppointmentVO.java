package com.dou.server.model.vo;

import com.dou.server.model.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AppointmentVO extends Appointment {

    @ApiModelProperty("预约监管仓名称")
    private String warehouseName;

    @ApiModelProperty("预约监管仓创建人")
    private Integer warehouseCreateUser;

    @ApiModelProperty("入仓开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date warehousingDateStart;

    @ApiModelProperty("入仓结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date warehousingDateEnd;

    @ApiModelProperty("货物类别名称")
    private String goodTypeName;

    @ApiModelProperty("货物来源名称")
    private String goodSourceName;

    @ApiModelProperty("审批备案状态")
    private Boolean showAuditfilingState;
}