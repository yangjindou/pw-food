package com.dou.server.tag;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.dou.server.exception.AuthException;
import com.dou.server.model.User;
import com.dou.server.utils.CommonUtils;
import com.dou.server.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token请求拦截器
 * @author yangjd
 * @date 2021-10-27
 */
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    private final RedisUtils redisUtils;

    public RequestInterceptor(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有PassToken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 如果没有token请求头，则返回错误信息
        String token = request.getHeader("token");
        if (CommonUtils.varIsBlank(token)) {
            throw new AuthException("缺少参数");
        }
        try {
            // 通过token解析获取userId，从redis中获取user对象，并且验证密码是否正确
            String userId = JWT.decode(token).getAudience().get(0);
            if (!redisUtils.hasKey(RedisUtils.USER_PREFIX + userId)) {
                throw new AuthException("用户已失效");
            }
            User user = (User) redisUtils.get(RedisUtils.USER_PREFIX + userId);
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getSecret())).build();
            jwtVerifier.verify(token);
            request.setAttribute("_user",user);
        } catch (Exception e) {
            log.error("token验证出错，异常原因：{}",e.getMessage());
            throw new AuthException();
        }
        return true;
    }
}