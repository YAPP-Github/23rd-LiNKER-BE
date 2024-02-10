package com.imlinker.storage.tag;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagCrawlingJpaRepository extends JpaRepository<TagCrawlingEntity, Long> {
    Optional<TagCrawlingEntity> findByTagIdAndPlatform(Long tagId, String name);
}
