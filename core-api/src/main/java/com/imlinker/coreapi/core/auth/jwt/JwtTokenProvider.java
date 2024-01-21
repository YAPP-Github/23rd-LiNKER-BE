package com.imlinker.coreapi.core.auth.jwt;

import com.imlinker.coreapi.support.exception.FilterExceptionHandler;
import com.imlinker.domain.common.Email;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtTokenProperties jwtTokenProperties;
    private final FilterExceptionHandler filterExceptionHandler;

    public String generateToken(Email email, TokenType tokenType) {
        TokenProperties properties =
                tokenType == TokenType.ACCESS_TOKEN
                        ? jwtTokenProperties.getAccessToken()
                        : jwtTokenProperties.getRefreshToken();

        Instant now = Instant.now();
        Instant expire = now.plus(properties.getExpire(), ChronoUnit.MILLIS);

        return Jwts.builder()
                .claim("email", email.getValue())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expire))
                .signWith(Keys.hmacShaKeyFor(properties.getSecret().getBytes()))
                .compact();
    }

    public Claims parseClaims(String token, TokenType tokenType) {
        try {
            SecretKey key =
                    Keys.hmacShaKeyFor(
                            (tokenType == TokenType.ACCESS_TOKEN
                                            ? jwtTokenProperties.getAccessToken()
                                            : jwtTokenProperties.getRefreshToken())
                                    .getSecret()
                                    .getBytes());

            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        } catch (SecurityException e) {
            throw new ApplicationException(ErrorType.UNAUTHORIZED, "유효하지 않은 토큰입니다.", e.getCause());
        } catch (ExpiredJwtException e) {
            throw new ApplicationException(ErrorType.UNAUTHORIZED, "만료된 토큰입니다.", e.getCause());
        } catch (DecodingException e) {
            throw new ApplicationException(ErrorType.UNAUTHORIZED, "잘못된 인증입니다.", e.getCause());
        } catch (MalformedJwtException e) {
            throw new ApplicationException(ErrorType.UNAUTHORIZED, "손상된 토큰입니다.", e.getCause());
        }
    }

    public Authentication generateAuthentication(Claims claims) {
        Email email = Email.of(claims.get("email").toString());
        return new UsernamePasswordAuthenticationToken(email, null, null);
    }
}
