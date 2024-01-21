package com.imlinker.coreapi.core.auth.oauth2;

import com.imlinker.coreapi.core.auth.oauth2.vendor.OAuthVendor;
import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private OAuthVendor vendor;
    private OAuth2User user;

    @Override
    public <T> T getAttribute(String name) {
        return user.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: 권한 부여
        return null;
    }

    @Override
    public String getName() {
        return user.getName();
    }
}
