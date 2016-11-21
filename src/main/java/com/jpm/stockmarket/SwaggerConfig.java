package com.jpm.stockmarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/exchange.*"))
                .build()
                .apiInfo(getApiInfo());
        return docket;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Stock Market Service",     // title
                "Stock Market Service",                     // description
                "0.1.0",                     // version
                "",                     // terms of service url
                new Contact("", "", ""), "proprietary",                     // license
                ""                      // licence url
        );
    }
}
