package com.imlinker.coreapi.core.auth.controller;

import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.enums.OperationResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping
    public ApiResponse<OperationResult> signUp() {
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
