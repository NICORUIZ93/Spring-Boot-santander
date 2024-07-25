package com.santander.santander.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Productos Santander")
                        .version("1.0")
                        .description("API para gestionar productos en la aplicación Santander.")
                        .termsOfService("http://localhost:8080/terms")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .url("http://localhost:8080/support")
                                .email("soporte@santander.com"))
                        .license(new License()
                                .name("Licencia MIT")
                                .url("https://opensource.org/licenses/MIT")));
    }
}