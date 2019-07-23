package com.zfr.aaron.spring.project.config;

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
 * Swagger文档配置类
 *
 * @author zfr
 */
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true", matchIfMissing = false)
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

  private static final String VERSION = "1.0.0";

  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Franchisee API")
        .description("")
        .license("")
        .licenseUrl("")
        .termsOfServiceUrl("")
        .version(VERSION)
        //.contact(new Contact("developer", "", ""))
        .build();
  }

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .apis(RequestHandlerSelectors.basePackage("com.zfr.aaron.spring.controller"))
        .paths(PathSelectors.any())
        .build();
  }
}
