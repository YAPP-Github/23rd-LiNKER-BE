package com.imlinker.coreapi.core.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenProperties {
    private final Long expire;
    private final String secret;
}
