package com.imlinker.coreapi.support.docs;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(AuthenticatedUserContextHolder.class);
        SecurityScheme securityScheme =
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("BearerAuth", securityScheme))
                .info(new Info().title("LINKER Core API").description("LINKER Core API"))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
    }
}
