import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

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
                .servers(List.of(new Server().url("http://localhost:8000").description("Local server"),
                        new Server().url("https://spring-commerce.herokuapp.com").description("Heroku server")))
                .tags(List.of(
                        new Tag().name("Authentication API").description("Endpoints for user authentication and registration"),
                        new Tag().name("Category API").description("Endpoints for category management"),
                        new Tag().name("Cart API").description("Endpoints for cart management"),
                        new Tag().name("Product API").description("Endpoints for product management")
                ));
    }
}