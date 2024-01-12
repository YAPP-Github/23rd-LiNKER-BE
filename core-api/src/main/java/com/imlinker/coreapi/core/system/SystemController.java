package com.imlinker.coreapi.core.system;

import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
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

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        log.info("[HealthController][Ping]");
        return ApiResponse.success("pong");
    }

    @GetMapping("favicon.ico")
    public HttpEntity<?> favicon() {
        return ResponseEntity.EMPTY;
    }

    @GetMapping("/unhandled-exception")
    public void triggerUnhandledError() throws Exception {
        throw new Exception("Error triggered");
    }

    @GetMapping("/handled-exception")
    public void triggerHandledError() {
        throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
    }
}
