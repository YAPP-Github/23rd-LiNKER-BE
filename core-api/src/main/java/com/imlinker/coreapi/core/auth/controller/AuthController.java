package com.imlinker.coreapi.core.auth.controller;

import com.imlinker.coreapi.core.auth.controller.request.ReIssueTokenRequest;
import com.imlinker.coreapi.core.auth.security.jwt.JwtTokenRefresher;
import com.imlinker.coreapi.core.auth.security.jwt.TokenResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth API", description = "인증 관련 API")
public class AuthController {

    private final JwtTokenRefresher refresher;

    @PostMapping("/token/re-issue")
    public ApiResponse<TokenResponse> reissueAccessToken(@RequestBody ReIssueTokenRequest request) {
        TokenResponse response = refresher.refresh(request.accessToken(), request.refreshToken());

        return ApiResponse.success(response);
    }
}
