package com.imlinker.error;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ErrorType errorType;
    private final Object data;
    private final Throwable cause;

    public ApplicationException(ErrorType errorType, Object data, Throwable cause) {
        super(errorType.getMessage(), cause);
        this.errorType = errorType;
        this.data = data;
        this.cause = cause;
    }

    public ApplicationException(ErrorType errorType) {
        this(errorType, errorType.getMessage(), null);
    }
}
