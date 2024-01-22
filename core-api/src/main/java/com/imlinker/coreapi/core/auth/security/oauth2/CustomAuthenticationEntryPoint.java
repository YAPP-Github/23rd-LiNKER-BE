package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import com.imlinker.error.ErrorType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final FilterExceptionHandler filterExceptionHandler;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {
        filterExceptionHandler.sendErrorMessage(response, ErrorType.UNAUTHENTICATED, authException);
    }
}
