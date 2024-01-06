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
    USER_NOT_FOUND(NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
    TOKEN_INVALID(UNAUTHORIZED, "유효하지 않은 토큰입니다.");

    private final int code;
    private final String message;

    ErrorMessage(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }
}
