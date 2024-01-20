package com.imlinker.storage.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagJpaRepository extends JpaRepository<UserTagEntity, Long> {

    List<UserTagEntity> findAllByUserId(Long userId);
}
