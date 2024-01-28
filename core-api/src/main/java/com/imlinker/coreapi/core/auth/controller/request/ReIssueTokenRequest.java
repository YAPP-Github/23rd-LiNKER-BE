package com.imlinker.coreapi.core.auth.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReIssueTokenRequest(
        @Schema(description = "만료된 AccessToken") String accessToken,
        @Schema(description = "유효한 RefreshToken") String refreshToken) {}
