package com.imlinker.storage.tag.mapper;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.storage.tag.TagEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagMapper {

    public static Tag toModel(TagEntity entity) {
        return new Tag(entity.getId(), entity.getSection(), entity.getName());
    }

    public static TagEntity toEntity(Tag model) {
        return new TagEntity(model.getId(), model.getSection(), model.getName());
    }
}
