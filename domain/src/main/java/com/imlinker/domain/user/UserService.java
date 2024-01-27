package com.imlinker.domain.user;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.user.model.MyProfile;
import com.imlinker.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserUpdater userUpdater;
    private final UserContactReader userContactReader;
    private final UserInterestReader userInterestReader;
    private final UserScheduleReader userScheduleReader;

    public MyProfile getMyProfile(Long userId){
        User user = userReader.findById(userId);

    }
    public User findByOAuthInfo(OAuthVendor oAuthVendor, String oAuthIdentifier) {
        return userReader.findByOAuthInfo(oAuthVendor,oAuthIdentifier);
    }

    public boolean isMember(OAuthVendor oAuthVendor, String oAuthIdentifier) {
       return userReader.existByOAuthInfo(oAuthVendor,oAuthIdentifier);
    }

    public User create(
            String oAuthId, String name, Email email, URL profileImgUrl, OAuthVendor oAuthVendor) {
        log.info(
                "[회원가입][시작] oAuthId: {}, name: {}, email: {}, profileImgUrl: {}, oAuthVendor: {}",
                oAuthId,
                name,
                email,
                profileImgUrl,
                oAuthVendor);

        return userUpdater.create(
                name,
                email,
                profileImgUrl,
                oAuthId,
                oAuthVendor
        );
    }
}
