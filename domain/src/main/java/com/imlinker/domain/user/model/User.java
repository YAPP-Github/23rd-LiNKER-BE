package com.imlinker.domain.user.model;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private OAuthVendor oAuthVendor;

    private String oAuthIdentifier;

    private String name;

    private Email email;

    private URL profileImgUrl;

    private String refreshToken;

    public User update(String name, Email email) {
        this.name = name;
        this.email = email;
        return this;
    }

    public User update(URL profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
        return this;
    }

    public User update(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
