package com.imlinker.coreapi.core.auth.security.oauth2.vendor;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import java.util.Map;

public interface OAuthVendorAttributeResolver {

    OAuthVendor getVendor();

    String getOAuthId(Map<String, Object> attributes);

    Email getEmail(Map<String, Object> attributes);

    String getProfileImgUrl(Map<String, Object> attributes);

    String getNickname(Map<String, Object> attributes);
}
