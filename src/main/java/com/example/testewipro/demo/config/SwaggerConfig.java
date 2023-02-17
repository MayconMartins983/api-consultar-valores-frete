package com.example.testewipro.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(info());
    }

    private static Info info() {
        return new Info()
                .title("API to query CEP and Freight")
                .description("Search here for information on addresses and shipping costs for each region")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://springdoc.org")
                )
                .version("1.0.0");
    }
}
