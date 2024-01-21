package com.imlinker.coreapi.core.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperties {
    private final TokenProperties accessToken;
    private final TokenProperties refreshToken;
}
