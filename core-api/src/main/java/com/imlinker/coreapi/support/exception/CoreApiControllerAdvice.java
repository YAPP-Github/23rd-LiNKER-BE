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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CoreApiControllerAdvice {

    private final Environment env;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(ApplicationException.class)
    public ApiResponse<Object> handleApplicationException(ApplicationException e) {
        return ApiResponse.error(e.getErrorType(), toErrorData(e.getData()), toDebugData(e.getCause()));
    }

    @ExceptionHandler(Throwable.class)
    public ApiResponse<Object> handleThrowable(Throwable throwable) {
        return ApiResponse.error(
                ErrorType.INTERNAL_PROCESSING_ERROR, throwable.getMessage(), toDebugData(throwable));
    }

    private String toErrorData(Object errorData) {
        try {
            return objectMapper.writeValueAsString(errorData);
        } catch (Exception e) {
            return null;
        }
    }

    private String toDebugData(Throwable throwable) {
        if (!isDevProfile()) {
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
