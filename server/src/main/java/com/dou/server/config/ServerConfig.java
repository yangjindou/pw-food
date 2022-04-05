package com.dou.server.config;

import com.dou.server.tag.MyJsonMapper;
import com.dou.server.tag.RequestInterceptor;
import com.dou.server.utils.RedisUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务配置
 * @author yangjd
 * @date 2021-10-27
 */
@Configuration
public class ServerConfig implements WebMvcConfigurer {

    @Resource
    RedisUtils redisUtils;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //在json转换之前先进行string转换
        converters.add(new StringHttpMessageConverter());
        //添加json转换
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(new MyJsonMapper());
        converters.add(0, jackson2HttpMessageConverter);
    }

    /**
     * 增加token验证拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(redisUtils))
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/**") // 静态资源文件
                .excludePathPatterns("/test")
                .excludePathPatterns("/swagger**/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/doc.html");
    }

    /**
     * cors处理跨域
     */
    @Bean
    public FilterRegistrationBean<?> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 是否支持安全证书
        config.addAllowedOrigin("*"); // 允许任何域名使用
        config.addAllowedHeader("*"); // 允许任何头
        config.addAllowedMethod("*"); // 允许任何方法（post、get等）
        config.setMaxAge(3600L); // 预检请求的有效期，单位为秒
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}