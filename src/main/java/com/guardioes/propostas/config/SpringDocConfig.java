package com.guardioes.propostas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("PROPOSTAS API")
                                .description("API para gestão de propostas, micro-serviços e envio de mensagem via Kafka")
                                .version("v1")
                                .contact(new Contact().name("Elias Mathias Sand").email("elias.sand.pb@compasso.com.br"))
                );
    }
}
