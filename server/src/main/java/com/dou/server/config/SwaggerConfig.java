package com.dou.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yangjindou
 * @date 2022-03-30
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    /**
     * swagger配置
     */
    @Bean
    public Docket createRestApi() {
        // 返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.dou.server"))
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
}
