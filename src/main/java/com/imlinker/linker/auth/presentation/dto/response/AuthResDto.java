package com.imlinker.linker.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResDto {
    private String accessToken;
    private String refreshToken;
}
