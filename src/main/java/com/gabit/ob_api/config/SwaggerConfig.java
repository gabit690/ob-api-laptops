package com.gabit.ob_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Spring Boot Videogame API REST",
                "Library Api rest docs",
                "V.1.0",
                "http://www.termsOfService.com",
                new Contact("Gabit", "http://www.gabit.com", "gabit@dev.com"),
                "MIT",
                "http://www.mitlicense.com",
                Collections.emptyList());
    }
}
