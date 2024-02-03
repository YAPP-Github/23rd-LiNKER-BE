package com.imlinker.domain.user.model;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import lombok.Builder;

@Builder
public record User(
        Long id,
        OAuthVendor oAuthVendor,
        String oAuthIdentifier,
        String name,
        Email email,
        URL profileImgUrl,
        String refreshToken) {

    public User update(String name, Email email) {
        return new User(id, oAuthVendor, oAuthIdentifier, name, email, profileImgUrl, refreshToken);
    }

    public User update(URL profileImgUrl) {
        return new User(id, oAuthVendor, oAuthIdentifier, name, email, profileImgUrl, refreshToken);
    }

    public User update(String refreshToken) {
        return new User(id, oAuthVendor, oAuthIdentifier, name, email, profileImgUrl, refreshToken);
    }
}
