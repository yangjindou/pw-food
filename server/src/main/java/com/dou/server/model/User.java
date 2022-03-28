package com.dou.server.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.HttpContextUtils;
import com.dou.server.utils.RedisUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@NoArgsConstructor
@Table(name="base_user")
public class User implements Serializable {

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

    @ApiModelProperty("账号")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("公钥")
    private String publicKey;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("部门ID")
    private String deptId;

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
                .withClaim("name", name)
                .sign(Algorithm.HMAC256(secret));
    }

    public void createSecret() {
        secret = CommonUtils.randomStr(20);
    }

    public static User getRequestUser() {
        try {
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            //从header中获取user对象
            return (User) request.getAttribute("_user");
        } catch (Exception e) {
            return null;
        }
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
        publicKey = null;
        secret = null;
    }
}