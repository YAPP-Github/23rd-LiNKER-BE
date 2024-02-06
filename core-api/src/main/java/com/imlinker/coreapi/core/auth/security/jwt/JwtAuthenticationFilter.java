package com.imlinker.coreapi.core.auth.security.jwt;

import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import com.imlinker.error.ErrorType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DecodingException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final FilterExceptionHandler filterExceptionHandler;
    private final List<String> whiteList = List.of("/api/v1/auth/token/re-issue");

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException {
        try {
            String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
            String token = ((HttpServletRequest) servletRequest).getHeader("Authorization");
            if (!whiteList.contains(requestUri) && token != null && token.startsWith("Bearer ")) {
                log.info("[인증필터][시작] token: {}", token);
                Claims claims =
                        jwtTokenProvider.parseClaims(token.replace("Bearer ", ""), TokenType.ACCESS_TOKEN);
                if (claims != null) {
                    SecurityContextHolder.getContext()
                            .setAuthentication(jwtTokenProvider.generateAuthentication(claims));
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SecurityException e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.UNAUTHORIZED,
                    "유효하지 않은 토큰입니다.",
                    e.getCause());
        } catch (ExpiredJwtException e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.UNAUTHORIZED,
                    "만료된 토큰입니다.",
                    e.getCause());
        } catch (DecodingException e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.UNAUTHORIZED,
                    "잘못된 인증입니다.",
                    e.getCause());
        } catch (MalformedJwtException e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.UNAUTHORIZED,
                    "손상된 토큰입니다.",
                    e.getCause());
        } catch (Exception e) {
            filterExceptionHandler.sendErrorMessage(
                    (HttpServletResponse) servletResponse,
                    ErrorType.INTERNAL_PROCESSING_ERROR,
                    ErrorType.INTERNAL_PROCESSING_ERROR.getMessage(),
                    e.getCause());
        }
    }
}
