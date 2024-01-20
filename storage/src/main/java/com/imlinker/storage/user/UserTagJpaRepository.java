package com.imlinker.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTagJpaRepository extends JpaRepository<UserTagEntity,Long> {

    List<UserTagEntity> findAllByUserId(Long userId);
}
