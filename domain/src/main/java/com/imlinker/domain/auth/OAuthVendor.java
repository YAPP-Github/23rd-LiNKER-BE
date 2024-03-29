package com.imlinker.domain.auth;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;

public enum OAuthVendor {
    KAKAO;

    public static OAuthVendor of(String vendor) {
        try {
            return OAuthVendor.valueOf(vendor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR, null, e);
        }
    }
}
