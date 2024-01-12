package com.imlinker.coreapi.support.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CoreApiControllerAdvice {

    private final Environment env;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Object>> handleApplicationException(ApplicationException e) {
        return handleException(e);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<Object>> handleThrowable(Throwable throwable) {
        return handleException(
                new ApplicationException(
                        ErrorType.INTERNAL_PROCESSING_ERROR, throwable.getMessage(), throwable));
    }

    private ResponseEntity<ApiResponse<Object>> handleException(ApplicationException e) {
        return new ResponseEntity<>(
                ApiResponse.error(e.getErrorType(), toErrorData(e.getData()), toDebugData(e.getCause())),
                HttpStatus.valueOf(e.getErrorType().getStatus()));
    }

    private String toErrorData(Object errorData) {
        if (errorData == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(errorData);
        } catch (Exception e) {
            return null;
        }
    }

    private String toDebugData(Throwable throwable) {
        if (!isDevProfile() || throwable == null) {
            return null;
        }
        try {
            StringWriter sw = new StringWriter();
            throwable.printStackTrace(new PrintWriter(sw));

            return sw.toString();
        } catch (Exception e) {
            return null;
        }
    }

    private Boolean isDevProfile() {
        String[] activeProfiles = env.getActiveProfiles();

        if (activeProfiles.length == 0) {
            return true;
        } else {
            Set<String> devProfiles = new HashSet<>(Arrays.asList("local", "dev"));
            return Arrays.stream(activeProfiles).anyMatch(devProfiles::contains);
        }
    }
}
