package com.dou.server.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yangjd
 */
@Data
@NoArgsConstructor
@Table(name="base_dept")
public class Dept implements Serializable {

    @Id
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("创建日期")
    private Date createDate;

    @ApiModelProperty("创建人")
    private Integer createUser;

    @ApiModelProperty("修改日期")
    private Date updateDate;

    @ApiModelProperty("修改人")
    private Integer updateUser;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("公钥")
    private String publicKey;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("部门")
    private String department;

    @Transient
    @ApiModelProperty("token（返回给前端）")
    private String token;

    @Transient
    @ApiModelProperty("secret（保存到redis中作验证）")
    private String secret;
}