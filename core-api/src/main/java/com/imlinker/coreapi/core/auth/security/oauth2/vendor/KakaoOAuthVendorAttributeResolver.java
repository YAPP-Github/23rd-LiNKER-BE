package com.imlinker.coreapi.core.auth.security.oauth2.vendor;

import com.imlinker.domain.common.Email;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class KakaoOAuthVendorAttributeResolver implements OAuthVendorAttributeResolver {
    @Override
    public OAuthVendor getVendor() {
        return OAuthVendor.KAKAO;
    }

    @Override
    public String getOAuthId(Map<String, Object> attributes) {
        return attributes.get("id").toString();
    }

    @Override
    public Email getEmail(Map<String, Object> attributes) {
        Map<String, String> emailAttributes = (Map<String, String>) attributes.get("kakao_account");
        return Email.of(emailAttributes.get("email"));
    }

    @Override
    public String getProfileImgUrl(Map<String, Object> attributes) {
        Map<String, String> emailAttributes = (Map<String, String>) attributes.get("properties");
        return emailAttributes.get("thumbnail_image");
    }

    @Override
    public String getNickname(Map<String, Object> attributes) {
        Map<String, String> emailAttributes = (Map<String, String>) attributes.get("properties");
        return emailAttributes.get("nickname");
    }
}
