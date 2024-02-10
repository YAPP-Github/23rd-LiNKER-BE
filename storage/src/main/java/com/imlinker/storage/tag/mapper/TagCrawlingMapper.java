package com.imlinker.storage.tag.mapper;

import com.imlinker.domain.tag.model.TagCrawling;
import com.imlinker.storage.tag.TagCrawlingEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagCrawlingMapper {
    public static TagCrawling toModel(TagCrawlingEntity entity) {
        return new TagCrawling(
                entity.getId(), entity.getTagId(), entity.getPlatform(), entity.getSection());
    }

    public static TagCrawlingEntity toEntity(TagCrawling model) {
        return new TagCrawlingEntity(model.id(), model.tagId(), model.platform(), model.section());
    }
}
