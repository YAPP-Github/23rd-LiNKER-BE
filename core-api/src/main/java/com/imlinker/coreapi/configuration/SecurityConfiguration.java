package com.imlinker.coreapi.configuration;

import com.imlinker.coreapi.core.auth.CustomAuthenticationSuccessHandler;
import com.imlinker.coreapi.core.auth.CustomOAuth2UserService;
import com.imlinker.coreapi.core.auth.StatefulParameterOAuth2AuthorizationRequestResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final StatefulParameterOAuth2AuthorizationRequestResolver
            statefulParameterOAuth2AuthorizationRequestResolver;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        http.oauth2Login(
                loginHandler ->
                        loginHandler
                                .successHandler(customAuthenticationSuccessHandler)
                                .userInfoEndpoint(
                                        userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService))
                                .authorizationEndpoint(
                                        authorizationEndpoint ->
                                                authorizationEndpoint.authorizationRequestResolver(
                                                        statefulParameterOAuth2AuthorizationRequestResolver)));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
                web.ignoring()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger/**",
                                "/favicon.ico");
    }
}
