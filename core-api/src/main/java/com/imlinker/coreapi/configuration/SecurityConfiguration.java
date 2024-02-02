package com.imlinker.coreapi.configuration;

import com.imlinker.coreapi.core.auth.security.jwt.JwtAuthenticationFilter;
import com.imlinker.coreapi.core.auth.security.jwt.JwtTokenProperties;
import com.imlinker.coreapi.core.auth.security.jwt.JwtTokenProvider;
import com.imlinker.coreapi.core.auth.security.jwt.TokenProperties;
import com.imlinker.coreapi.core.auth.security.oauth2.*;
import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties({JwtTokenProperties.class, TokenProperties.class})
public class SecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    private final FilterExceptionHandler filterExceptionHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthorizationRequestResolver customOAuth2AuthorizationRequestResolver;

    private final String[] ignoredPath = {
        "/ping",
        "/h2-console/**",
        "/error-types",
        "/favicon.ico",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/swagger/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(
                        httpSecurityHeadersConfigurer ->
                                httpSecurityHeadersConfigurer.frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        // oAuthHandler
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

        // SpringSecurity ExceptionHandler
        http.exceptionHandling(
                exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(customAccessDeniedHandler)
                                .authenticationEntryPoint(customAuthenticationEntryPoint));

        // Authorization Check
        http.authorizeHttpRequests(
                authorization ->
                        authorization
                                .requestMatchers(ignoredPath)
                                .permitAll()
                                .requestMatchers("/oauth2/**", "/api/v1/auth/**", "/api/v1/tags/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated());

        // CustomFilter
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtTokenProvider, filterExceptionHandler),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
