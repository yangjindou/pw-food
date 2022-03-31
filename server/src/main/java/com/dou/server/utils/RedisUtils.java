package com.dou.server.utils;

import com.dou.server.model.User;
import io.lettuce.core.RedisException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * @author yangjd
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 12 * 3600;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    /** 用户在redis中的前缀 **/
    public final static String USER_PREFIX = "user::";

    public void setUser(User user) {
        if (null != user) {
            set(RedisUtils.USER_PREFIX + user.getId(), user);
        }
    }

    /**
     * 设置key和value
     * @param key redis的key
     * @param value redis的值
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 设置key和value
     * @param key redis的key
     * @param value redis的值
     * @param expire 过期时间
     */
    public void set(String key, Object value, long expire) {
        if (expire < -1) {
            throw new RedisException("expire value is error ...");
        }
        if (expire == NOT_EXPIRE){
            redisTemplate.opsForValue().set(key, value);
        } else {
            redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 获取key的值
     * @param key redis的key
     * @return obj
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 删除key和值
     * @param key redis的key
     */
    public void delete(String... key) {
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 判断key值在redis中是否存在
     * @param key key
     * @return true or false
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
