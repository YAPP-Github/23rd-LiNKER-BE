package com.imlinker.domain.user;

import com.imlinker.domain.common.Email;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(Email email);

    User save(User user);

}
