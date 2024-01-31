package com.imlinker.coreapi.support.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FilterExceptionHandler {

    private final ObjectMapper objectMapper;

    public void sendErrorMessage(
            HttpServletResponse response, ErrorType errorType, Object data, Throwable cause)
            throws IOException {
        String dataMessage = data == null ? "" : objectMapper.writeValueAsString(data);
        String message = cause == null ? errorType.getMessage() : cause.getMessage();
        log.error("ErrorType: {}, Message: {}", errorType, message);
        ApiResponse<Object> failure = ApiResponse.error(errorType, dataMessage, message);

        response.setStatus(errorType.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(failure));
    }
}
