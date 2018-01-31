package com.renhy.home.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Predicate;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Lists.newArrayList;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 Configuration
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${system.version.number}")
    private String versionNumber;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Renhy")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.renhy.home"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 配置 Swagger API 信息.
     * @return API Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("H3C IIoT Cloud Platform API")
            .description("H3C IIoT Cloud Platform API")
            .version(versionNumber)
            .build();
    }


}
