package com.imlinker.storage.news;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsJpaRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findByTagId(Long tagId);

    Optional<NewsEntity> findByNewsUrl(String newsUrl);
}
