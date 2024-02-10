package com.imlinker.domain.tag.model;

import java.util.Optional;

public interface TagCrawlingRepository {
    TagCrawling findById(Long id);

    Optional<TagCrawling> findByTagIdAndPlatform(Long tagId, String name);

    TagCrawling save(TagCrawling tagCrawling);
}
