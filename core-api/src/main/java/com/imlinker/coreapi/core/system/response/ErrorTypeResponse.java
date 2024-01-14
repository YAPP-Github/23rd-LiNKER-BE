package com.imlinker.coreapi.core.system.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorTypeResponse {
    private String code;
    private String message;
    private int status;
}
