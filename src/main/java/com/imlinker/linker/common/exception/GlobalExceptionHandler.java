package com.imlinker.linker.common.exception;


import com.imlinker.linker.common.exception.custom.BusinessException;
import com.imlinker.linker.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandle(BusinessException e) {
        log.warn("businessException : {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allUncaughtHandle(Exception e) {
        log.error("allUncaughtHandle : {}", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("methodArgumentNotValidException : {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), e.getFieldErrors().get(0).getDefaultMessage()));
    }
}
