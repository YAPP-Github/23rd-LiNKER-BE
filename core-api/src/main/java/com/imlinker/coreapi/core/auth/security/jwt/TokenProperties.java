package com.imlinker.coreapi.core.auth.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    private final Long expire;
    private final String secret;
}
