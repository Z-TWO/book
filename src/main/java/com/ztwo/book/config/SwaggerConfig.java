package com.ztwo.book.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author ZTwo
 * @Date 2021/12/20 12:41
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger2.enable}")
public class SwaggerConfig {

    @Value("${jwt.header}")
    private String HEADER_AUTH;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //进入swagger-ui的信息
                .apiInfo(apiInfo())
                .select()
                //暴露所有controller类的所在的包路径
                .apis(RequestHandlerSelectors.basePackage("com.ztwo.book.api"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(new ArrayList<SecurityScheme>(Arrays.asList(apiKey())));
    }

    /**
     * 构建Authorization验证key
     *
     * @return
     */
    private ApiKey apiKey() {
        return new ApiKey(HEADER_AUTH, HEADER_AUTH, "header");
    }

    //进入swagger-ui的信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //该项目的名字
                .title("共享图书管理系统")
                //该项目的描述
                .description("使用springboot开发共享图书管理系统")
                .version("1.0.1")
                .build();
    }
}
