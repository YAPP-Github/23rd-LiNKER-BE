package com.imlinker.coreapi.core.auth.security.jwt;

import com.imlinker.domain.auth.AuthService;
import com.imlinker.domain.user.model.User;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
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
        try {
            Long userId =
                    Long.parseLong(
                            provider.parseClaims(accessToken, TokenType.ACCESS_TOKEN).get("id").toString());
            log.info("[토큰][재발급][시작] (expired=false, userId={})", userId);
            return refresh(userId, refreshToken);
        } catch (ExpiredJwtException e) {
            Long userId = Long.parseLong(e.getClaims().get("id").toString());
            log.info("[토큰][재발급][시작] (expired=true, userId={})", userId);
            return refresh(userId, refreshToken);
        }
    }

    private TokenResponse refresh(Long userId, String refreshToken) {
        User user = authService.findByUserId(userId);
        if (user.getRefreshToken() == null || !user.getRefreshToken().equals(refreshToken)) {
            log.info("[AccessToken][재발급][실패] refreshToken이 존재하지 않거나, 일치하지 않음 (userId={})", userId);
            throw new ApplicationException(ErrorType.UNAUTHORIZED);
        }

        Date expiration = provider.parseClaims(refreshToken, TokenType.REFRESH_TOKEN).getExpiration();
        if (expiration.before(new Date())) {
            log.info("[AccessToken][재발급][실패] 이미 만료된 RefreshToken (userId={})", userId);
            throw new ApplicationException(ErrorType.UNAUTHORIZED);
        }

        String reIssuedAccessToken =
                provider.generateToken(user.getId(), user.getEmail(), TokenType.ACCESS_TOKEN);

        return new TokenResponse(reIssuedAccessToken, refreshToken);
    }
}
