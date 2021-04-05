package com.mhm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by MHm on 2019/6/12.
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerConfiguration {

    @Value("${swagger.basePackage:com.mhm}")
    private String basePackage;
    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        .enable(swaggerEnable)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.any())
        .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        .title("RESTful API")
        .description("后台api接口文档")
        .version("1.0")
        .build();
    }
}
