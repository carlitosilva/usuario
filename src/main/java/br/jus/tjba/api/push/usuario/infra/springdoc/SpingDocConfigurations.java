package br.jus.tjba.api.push.usuario.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpingDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                .title("API Spring Boot Push Judicial")
                .description("API Rest da aplicação Exercício - Trilha Spring Boot Avançada")
                .contact(new Contact()
                .name("Time Backend")
                .email("backend@dev.io"))
                .license(new License()
                .name("Apache 2.0")
                .url("http://127.0.0.1:8082/usuario/licenca")));
    }

}
