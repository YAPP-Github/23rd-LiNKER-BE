package com.imlinker.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserInterestJpaRepository extends JpaRepository<UserInterestEntity, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from UserInterestEntity ui where ui.userId = :userId")
    void deleteAllByUserId(Long userId);
}
