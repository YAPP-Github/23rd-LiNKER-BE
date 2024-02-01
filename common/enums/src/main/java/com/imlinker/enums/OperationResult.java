package com.imlinker.enums;

public enum OperationResult {
    SUCCESS,
    FAIL;

    public static OperationResult of(boolean isSuccess) {
        return isSuccess ? SUCCESS : FAIL;
    }
}
