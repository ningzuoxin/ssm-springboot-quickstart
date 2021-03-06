package com.ning.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder builder = new ParameterBuilder();
        builder.defaultValue("token")
                .description("令牌")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .name("token")
                .required(false)
                .build();
        springfox.documentation.service.Parameter parameter = builder.build();
        parameters.add(parameter);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) // 这里采用包含注解的方式来确定要显示的接口
                // .apis(RequestHandlerSelectors.basePackage("com.ning.modular.controller")) // 这里采用包扫描的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SSM-SpringBoot-QuickStart RESTful APIs")
                .description("SSM-SpringBoot-QuickStart 接口文档")
                .termsOfServiceUrl("https://doc.xiaominfo.com/")
                .contact(new Contact("Mr.code", "http://localhost:9999/doc.html", ""))
                .version("1.0")
                .build();
    }

}
