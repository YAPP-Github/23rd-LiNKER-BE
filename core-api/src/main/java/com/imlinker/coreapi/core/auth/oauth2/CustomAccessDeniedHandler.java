package com.imlinker.coreapi.core.auth.oauth2;

import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final FilterExceptionHandler filterExceptionHandler;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        filterExceptionHandler.sendErrorMessage(
                response, ErrorType.UNAUTHORIZED, accessDeniedException);
    }
}
