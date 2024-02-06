package com.imlinker.domain.auth;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.user.UserReader;
import com.imlinker.domain.user.UserUpdater;
import com.imlinker.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserReader userReader;
    private final UserUpdater userUpdater;

    public User findByUserId(Long userId) {
        return userReader.findById(userId);
    }

    public User findByOAuthInfo(OAuthVendor oAuthVendor, String oAuthIdentifier) {
        return userReader.findByOAuthInfo(oAuthVendor, oAuthIdentifier);
    }

    public boolean isMember(OAuthVendor oAuthVendor, String oAuthIdentifier) {
        return userReader.existByOAuthInfo(oAuthVendor, oAuthIdentifier);
    }

    public User create(
            String oAuthId, String name, Email email, URL profileImgUrl, OAuthVendor oAuthVendor) {
        log.info(
                "[회원가입][시작] oAuthId: {}, name: {}, email: {}, oAuthVendor: {}",
                oAuthId,
                name,
                email,
                oAuthVendor);

        return userUpdater.create(name, email, profileImgUrl, oAuthId, oAuthVendor);
    }

    public User updateRefreshToken(Long id, String refreshToken) {
        return userUpdater.updateRefreshToken(id, refreshToken);
    }
}
