package com.imlinker.storage.tag.mapper;

import com.imlinker.domain.tag.model.Platform;
import com.imlinker.storage.tag.PlatformEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlatformMapper {
    public static Platform toModel(PlatformEntity entity) {
        return new Platform(entity.getId(), entity.getName(), entity.getSection());
    }

    public static PlatformEntity toEntity(Platform model) {
        return new PlatformEntity(model.id(), model.name(), model.section());
    }
}
