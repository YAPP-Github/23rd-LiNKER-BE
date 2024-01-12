package com.imlinker.coreapi.core.system;

import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.success("pong");
    }

    @GetMapping("/system-exception")
    public void triggerUnhandledError() throws Exception {
        throw new Exception("Error triggered");
    }

    @GetMapping("/application-exception")
    public void triggerHandledError() {
        throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
    }
}
