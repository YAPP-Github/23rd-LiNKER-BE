package com.imlinker.domain.user;

import com.imlinker.domain.auth.OAuthVendor;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByOAuthVendorAndOAuthIdentifier(
            OAuthVendor oAuthVendor, String oAuthIdentifier);

    User save(User user);
}
