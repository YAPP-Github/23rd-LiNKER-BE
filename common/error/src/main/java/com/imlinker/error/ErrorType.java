package com.imlinker.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    UNAUTHENTICATED(401, "UNAUTHENTICATED", "인증되지 않은 사용자입니다."),
    UNAUTHORIZED(403, "UNAUTHORIZED", "권한이 없는 사용자입니다."),
    INVALID_REQUEST_PARAMETER(400, "INVALID_REQUEST_PARAMETER", "잘못된 요청 파라미터 입니다."),
    INTERNAL_PROCESSING_ERROR(500, "INTERNAL_PROCESSING_ERROR", "내부 시스템 에러가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}
