package com.imlinker.coreapi.configuration;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final List<String> allowedOrigins =
            List.of("https://im-linker.com/", "http://localhost:3000");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins(allowedOrigins.toArray(new String[0]))
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticatedUserContextResolver());
    }
}
