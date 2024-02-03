package com.imlinker.coreapi.core.auth.security.jwt;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.domain.common.model.Email;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    public String generateToken(Long id, Email email, TokenType tokenType) {
        JwtTokenProperties.TokenProperties properties =
                tokenType == TokenType.ACCESS_TOKEN
                        ? jwtTokenProperties.getAccess()
                        : jwtTokenProperties.getRefresh();

        Instant now = Instant.now();
        Instant expire = now.plus(properties.getExpire(), ChronoUnit.MILLIS);

        return Jwts.builder()
                .claim("id", id)
                .claim("email", email.getValue())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expire))
                .signWith(Keys.hmacShaKeyFor(properties.getSecret().getBytes()))
                .compact();
    }

    public Claims parseClaims(String token, TokenType tokenType) {
        SecretKey key =
                Keys.hmacShaKeyFor(
                        (tokenType == TokenType.ACCESS_TOKEN
                                        ? jwtTokenProperties.getAccess()
                                        : jwtTokenProperties.getRefresh())
                                .getSecret()
                                .getBytes());

        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public Authentication generateAuthentication(Claims claims) {
        AuthenticatedUserContextHolder userContext =
                new AuthenticatedUserContextHolder(
                        Long.parseLong(claims.get("id").toString()), Email.of(claims.get("email").toString()));
        return new UsernamePasswordAuthenticationToken(userContext, null, null);
    }
}
