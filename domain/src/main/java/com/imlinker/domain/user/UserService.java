package com.imlinker.domain.user;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean isMember(OAuthVendor oAuthVendor, String oAuthIdentifier) {
        return userRepository
                .findByOAuthVendorAndOAuthIdentifier(oAuthVendor, oAuthIdentifier)
                .isPresent();
    }

    public User createUser(
            String oAuthId, String name, Email email, URL profileImgUrl, OAuthVendor oAuthVendor) {
        log.info(
                "[회원가입][시작] oAuthId: {}, name: {}, email: {}, profileImgUrl: {}, oAuthVendor: {}",
                oAuthId,
                name,
                email,
                profileImgUrl,
                oAuthVendor);
        return userRepository.save(
                User.builder()
                        .oAuthIdentifier(oAuthId)
                        .name(name)
                        .email(email)
                        .profileImgUrl(profileImgUrl)
                        .oAuthVendor(oAuthVendor)
                        .build());
    }
}
