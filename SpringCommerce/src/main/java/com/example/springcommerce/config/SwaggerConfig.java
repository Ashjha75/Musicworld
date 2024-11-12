package com.example.springcommerce.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                        new Info()
                                .title("Spring Commerce API")
                                .version("1.0")
                                .description("API for Spring Commerce")
                )
                .servers(List.of(new Server().url("http://localhost:8000").description("Local server")
                        , new Server().url("https://spring-commerce.herokuapp.com").description("Heroku server")
                ))
                .tags(List.of(new Tag().name("Authentication API")
                        , new Tag().name("Category API")
                        , new Tag().name("Cart API")
                        , new Tag().name("Product API")
                ));
    }
}
