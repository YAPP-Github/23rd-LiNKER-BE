package com.imlinker.linker.common.exception.enums;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버와의 연결에 실패하였습니다."),
    REDIS_CONNECTION_ERROR(INTERNAL_SERVER_ERROR, "redis 연결에 실패하였습니다."),
    USER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    KAKAO_TOKEN_INVALID(UNAUTHORIZED, "유효하지 않은 KAKAO 토큰입니다."),
    JWT_TOKEN_INVALID(UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다."),
    JWT_TOKEN_EXPIRED(UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    JWT_SIGNATURE_FAIL(UNAUTHORIZED, "잘못된 서명의 JWT 토큰입니다."),
    EMPTY_JWT(UNAUTHORIZED, "JWT 토큰을 입력해주세요."),
    UNSUPPORTED_JWT(UNAUTHORIZED, "잘못된 형식의 JWT 토큰입니다."),
    NOT_EXIST_REFRESH_JWT(UNAUTHORIZED, "존재하지 않거나 만료된 Refresh 토큰입니다. 다시 로그인해주세요."),
    SOCIAL_ID_INVALID(UNAUTHORIZED, "존재하지 않는 SOCIAL ID 입니다.");

    private final int code;
    private final String message;

    ErrorMessage(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }
}
