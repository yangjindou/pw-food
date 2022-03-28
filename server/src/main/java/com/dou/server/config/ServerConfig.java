package com.dou.server.config;

import com.dou.server.tag.RequestInterceptor;
import com.dou.server.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * 服务配置
 * @author yangjd
 * @date 2021-10-27
 */
@EnableOpenApi
@Configuration
public class ServerConfig implements WebMvcConfigurer {

    @Resource
    RedisUtils redisUtils;

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
     * swagger配置
     */
    @Bean
    public Docket createRestApi() {
        // 返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * swagger信息
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据平台开放接口")
                .description("Rest API接口")
                .termsOfServiceUrl("https://www.baidu.com")
                .version("1.0")
                .build();
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