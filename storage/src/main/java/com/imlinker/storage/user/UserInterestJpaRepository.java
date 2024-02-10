package com.imlinker.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestJpaRepository extends JpaRepository<UserInterestEntity, Long> {
    void deleteAllByUserId(Long userId);
}
