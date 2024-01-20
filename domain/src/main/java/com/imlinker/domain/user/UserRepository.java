package com.imlinker.domain.user;

public interface UserRepository {

    User findById(Long id);

    User save(User user);
}
