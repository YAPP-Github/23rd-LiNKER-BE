package com.imlinker.domain.user;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.user.model.User;
import com.imlinker.domain.user.model.UserRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.USER_NOT_FOUND));
    }
    public User findByOAuthInfo(
            OAuthVendor oAuthVendor,
            String oAuthIdentifier
    ){
        return userRepository
                .findByOAuthVendorAndOAuthIdentifier(oAuthVendor, oAuthIdentifier)
                .orElseThrow(() -> new ApplicationException(ErrorType.USER_NOT_FOUND));
    }

    public boolean existByOAuthInfo(
            OAuthVendor oAuthVendor,
            String oAuthIdentifier
    ){
        return userRepository
                .findByOAuthVendorAndOAuthIdentifier(oAuthVendor, oAuthIdentifier)
                .isPresent();
    }
}
