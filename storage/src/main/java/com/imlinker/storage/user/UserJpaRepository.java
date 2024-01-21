package com.imlinker.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
        Optional<UserEntity> findByEmail(String email);
}
