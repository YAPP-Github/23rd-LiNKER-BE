package com.imlinker.coreapi.core.auth.controller;

import com.imlinker.coreapi.core.auth.controller.request.SignUpRequest;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth API", description = "인증 관련 API")
public class AuthController {

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 하기")
    public ApiResponse<OperationResult> signUp(@RequestBody SignUpRequest request) {
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
