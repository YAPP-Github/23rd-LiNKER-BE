package com.imlinker.storage.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestJpaRepository extends JpaRepository<UserInterestEntity, Long> {

    List<UserInterestEntity> findAllByUserId(Long userId);
}
