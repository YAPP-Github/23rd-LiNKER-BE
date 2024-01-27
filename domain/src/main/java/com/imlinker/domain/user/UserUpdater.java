package com.imlinker.domain.user;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.user.model.User;
import com.imlinker.domain.user.model.UserRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public User create(
            String name,
            Email email,
            URL profileImgUrl,
            String oAuthIdentifier,
            OAuthVendor oAuthVendor) {
        return userRepository.save(
                new User(null, oAuthVendor, oAuthIdentifier, name, email, profileImgUrl));
    }

    public User fetch(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorType.USER_NOT_FOUND));
    }
}
