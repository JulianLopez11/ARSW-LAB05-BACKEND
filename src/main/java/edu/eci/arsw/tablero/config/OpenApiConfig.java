package edu.eci.arsw.tablero.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("ARSW Lab05 API")
                .version("v1")
                .description("Laboratorio 05 de ARSW - Tablero API"));
    }
}