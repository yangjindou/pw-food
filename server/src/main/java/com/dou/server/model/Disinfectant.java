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
@Table(name="t_disinfectant")
@Accessors(chain = true)
public class Disinfectant extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("消毒液名称")
    private String name;

    @ApiModelProperty("消毒液配比比例")
    private String ratio;

    @ApiModelProperty("调配时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date deploymentDate;

    @ApiModelProperty("调配人")
    private String deploymentUser;

    @ApiModelProperty("消毒液浓度")
    private String concentration;

    @ApiModelProperty("消杀评估")
    private String assessment;

    @Transient
    @ApiModelProperty("录入人")
    private String createUserName;
}