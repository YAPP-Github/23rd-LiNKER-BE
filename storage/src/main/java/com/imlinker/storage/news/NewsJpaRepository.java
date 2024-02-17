package com.imlinker.storage.news;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsJpaRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findByTagId(Long tagId);

    List<NewsEntity> findTop20ByTagIdInOrderByCreatedAtDesc(List<Long> tagIds);

    List<NewsEntity> findTop20ByTagIdOrderByCreatedAtDesc(Long tagId);

    Optional<NewsEntity> findByNewsUrl(String newsUrl);
}
