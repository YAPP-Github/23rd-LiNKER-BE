package com.imlinker.storage.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsJpaRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findBytagId(Long tagId);
}
