package com.imlinker.coreapi.core.auth.oauth2.vendor;

import com.imlinker.domain.common.Email;
import java.util.Map;

public interface OAuthVendorAttributeResolver {

    OAuthVendor getVendor();

    Email getEmail(Map<String, Object> attributes);
}
