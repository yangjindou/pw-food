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
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yangjd
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(name="base_user")
public class User extends BaseEntity implements Serializable {

    @Id
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
                .withExpiresAt(DateUtils.addSeconds(new Date(), Math.toIntExact(RedisUtils.DEFAULT_EXPIRE)))
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

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.createToken();
        System.out.println(user.getToken());
    }

    // 隐藏密码和公钥
    public void protectInfo() {
        password = null;
        salt = null;
        secret = null;
    }
}