package com.imlinker.coreapi.core.system;

import com.imlinker.coreapi.core.system.response.ErrorTypeResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.error.ErrorType;
import io.swagger.v3.oas.annotations.Hidden;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SystemController {

    @Hidden
    @ResponseBody
    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.success("pong");
    }

    @ResponseBody
    @GetMapping("/error-types")
    public ApiResponse<List<ErrorTypeResponse>> listErrorTypes() {
        return ApiResponse.success(
                Arrays.stream(ErrorType.values())
                        .map(type -> new ErrorTypeResponse(type.getCode(), type.getMessage(), type.getStatus()))
                        .toList());
    }

    @Hidden
    @GetMapping("/api-docs")
    public String apiDocs() {
        return "docs/index.html";
    }

    @Hidden
    @GetMapping("favicon.ico")
    public HttpEntity<?> favicon() {
        return ResponseEntity.EMPTY;
    }
}
