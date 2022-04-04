package com.dou.server.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.HttpContextUtils;
import com.dou.server.utils.RedisUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="sys_user")
@Accessors(chain = true)
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("是否启用（1为禁用，否则为启用）")
    private Integer enable;

    @ApiModelProperty("属地")
    private String area;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("社会统一信用代码")
    private String enterpriseCode;

    @ApiModelProperty("法人")
    private String enterpriseOwner;

    @ApiModelProperty("营业执照注册地址")
    private String enterpriseLicenseAddress;

    @ApiModelProperty("营业执照照片")
    private String enterpriseLicenseImg;

    @Transient
    @ApiModelProperty("token（返回给前端）")
    private String token;

    @Transient
    @ApiModelProperty("secret（保存到redis中作验证）")
    private String secret;

    public void createToken() {
        createSecret();
        token = JWT.create()
                // 发布者
                .withIssuer("dou")
                // 生成签名的时间
                .withIssuedAt(new Date())
                // 生成签名的有效期,小时
                .withExpiresAt(DateUtils.addSeconds(new Date(), Math.toIntExact(RedisUtils.USER_EXPIRE)))
                .withAudience(id.toString())
                .withClaim("name", getLoginName())
                .sign(Algorithm.HMAC256(secret));
    }

    public void createSecret() {
        secret = CommonUtils.randomStr(20);
    }

    public static User getRequestUser() {
        //从request中获取user对象
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        return (User) request.getAttribute("_user");
    }

    // 隐藏密码和公钥
    public User protectInfo() {
        password = null;
        salt = null;
        secret = null;
        return this;
    }
}