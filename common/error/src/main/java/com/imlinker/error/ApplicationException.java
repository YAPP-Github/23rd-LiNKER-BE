package com.imlinker.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException {
    private final ErrorType errorType;
    private final Object data;
    private final Throwable cause;
}
