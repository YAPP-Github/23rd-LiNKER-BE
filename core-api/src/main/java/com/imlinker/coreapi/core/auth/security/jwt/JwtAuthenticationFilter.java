package com.imlinker.coreapi.core.auth.security.jwt;

import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final FilterExceptionHandler filterExceptionHandler;

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException {
        try {
            String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                log.info("[인증필터][시작] token: {}", token);
                Claims claims =
                        jwtTokenProvider.parseClaims(token.replace("Bearer ", ""), TokenType.ACCESS_TOKEN);
                if (claims != null) {
                    SecurityContextHolder.getContext()
                            .setAuthentication(jwtTokenProvider.generateAuthentication(claims));
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ApplicationException e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse, e.getErrorType(), e.getData(), e.getCause());
        } catch (Exception e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.INTERNAL_PROCESSING_ERROR,
                    null,
                    e.getCause());
        }
    }
}
