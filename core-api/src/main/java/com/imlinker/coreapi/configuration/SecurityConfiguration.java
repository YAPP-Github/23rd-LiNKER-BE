package com.imlinker.coreapi.configuration;

import com.imlinker.coreapi.core.auth.jwt.JwtTokenProperties;
import com.imlinker.coreapi.core.auth.jwt.TokenProperties;
import com.imlinker.coreapi.core.auth.oauth2.CustomAccessDeniedHandler;
import com.imlinker.coreapi.core.auth.oauth2.CustomAuthenticationSuccessHandler;
import com.imlinker.coreapi.core.auth.oauth2.CustomAuthorizationRequestResolver;
import com.imlinker.coreapi.core.auth.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({JwtTokenProperties.class, TokenProperties.class})
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthorizationRequestResolver customOAuth2AuthorizationRequestResolver;

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
                                                        customOAuth2AuthorizationRequestResolver)));

        http.exceptionHandling(
                exceptionHandling -> exceptionHandling.accessDeniedHandler(customAccessDeniedHandler));

        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests.requestMatchers("/oauth/**").permitAll());

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
