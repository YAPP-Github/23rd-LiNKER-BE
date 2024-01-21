package com.imlinker.coreapi.core.auth.vendor;

public class KakaoOAuthVendorAttributeResolver implements OAuthVendorAttributeResolver {
    @Override
    public OAuthVendor getVendor() {
        return OAuthVendor.KAKAO;
    }
}
