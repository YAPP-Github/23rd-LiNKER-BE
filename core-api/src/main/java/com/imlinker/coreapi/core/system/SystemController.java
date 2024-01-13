package com.imlinker.coreapi.core.system;

import com.imlinker.coreapi.support.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SystemController {

    @Hidden
    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        log.info("[HealthController][Ping]");
        return ApiResponse.success("pong");
    }

    @Hidden
    @GetMapping("favicon.ico")
    public HttpEntity<?> favicon() {
        return ResponseEntity.EMPTY;
    }
}
