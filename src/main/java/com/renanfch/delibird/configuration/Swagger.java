package com.renanfch.delibird.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.renanfch.delibird"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        final var apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Communication Platform Delibird - Swagger API");
        apiInfoBuilder.description("Endpoint Access API Documentation with Swagger");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.licenseUrl("https://www.renanfch.com.br");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder.build();
    }

    private Contact contact() {
        return new Contact(
                "RenanFCH S/A",
                "https://www.renanfch.com.br",
                "");
    }
}
