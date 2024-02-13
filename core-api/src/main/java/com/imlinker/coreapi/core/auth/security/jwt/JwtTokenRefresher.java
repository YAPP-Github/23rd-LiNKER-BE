package com.imlinker.coreapi.core.auth.security.jwt;

import com.imlinker.domain.auth.AuthService;
import com.imlinker.domain.user.model.User;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenRefresher {

    private final JwtTokenProvider provider;
    private final AuthService authService;

    public TokenResponse refresh(String accessToken, String refreshToken) {
        Long userId = extractUserId(accessToken);
        User user = authService.findByUserId(userId);

        // if (user.refreshToken() == null || !user.refreshToken().equals(refreshToken)) {
        //    log.info("[AccessToken][재발급][실패] refreshToken이 존재하지 않거나, 일치하지 않음 (userId={})", user.id());
        //    throw new ApplicationException(ErrorType.UNAUTHORIZED);
        // }

        Claims claims = provider.parseClaims(refreshToken, TokenType.REFRESH_TOKEN);
        if (claims.getExpiration().before(new Date())) {
            log.info("[AccessToken][재발급][실패] 이미 만료된 RefreshToken (userId={})", user.id());
            throw new ApplicationException(ErrorType.UNAUTHORIZED, "만료된 RefreshToken 입니다.", null);
        }

        if (Long.parseLong(claims.get("id").toString()) != userId) {
            log.info(
                    "[AccessToken][재발급][실패] RefreshToken의 id와 AccessToken의 id가 일치하지 않음 (userId={})",
                    user.id());
            throw new ApplicationException(
                    ErrorType.UNAUTHORIZED, "AccessToken과 RefresToken의 사용자가 일치하지 않습니다.", null);
        }

        String reIssuedAccessToken =
                provider.generateToken(user.id(), user.email(), TokenType.ACCESS_TOKEN);

        return new TokenResponse(reIssuedAccessToken, refreshToken);
    }

    private Long extractUserId(String token) {
        try {
            return Long.parseLong(
                    provider.parseClaims(token, TokenType.ACCESS_TOKEN).get("id").toString());
        } catch (ExpiredJwtException e) {
            return Long.parseLong(e.getClaims().get("id").toString());
        } catch (Exception e) {
            log.error("[토큰][오류] 잘못된 형식의 토큰 (error={})", e.getMessage());
            throw new ApplicationException(ErrorType.UNAUTHORIZED);
        }
    }
}
