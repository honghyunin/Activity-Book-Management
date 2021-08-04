package com.CRUD.test.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo(){ // ApiInfo는 Swagger-ui에서 메인으로 보여질 정볼르 설정합니다
        return new ApiInfoBuilder()
                .title("스웨거 테스트")
                .description("REST API 스웨거 테스트")
                .build();
    }

    public Docket commonApi(){ // Docker은 api의 그룹명이나 이동경로, 보여질 api가 속한 패키지 등의 자세한 정보를 담습니다.
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("스웨거 테스트")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.demo.controller"))
                .paths(PathSelectors.ant("/api/**")) //controler의 어떤 경로로 데이터를 전달할 수 있도록 할 것인지 정합니다.
                .build();
    }

}
