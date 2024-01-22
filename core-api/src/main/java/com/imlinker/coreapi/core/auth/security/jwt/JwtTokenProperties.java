package com.imlinker.coreapi.core.auth.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperties {
    private final TokenProperties access;
    private final TokenProperties refresh;

    @Getter
    @AllArgsConstructor
    public static class TokenProperties {
        private final Long expire;
        private final String secret;
    }
}
