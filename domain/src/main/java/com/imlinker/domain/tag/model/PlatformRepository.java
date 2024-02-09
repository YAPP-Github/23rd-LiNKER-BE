package com.imlinker.domain.tag.model;

public interface PlatformRepository {
    Platform findById(Long id);

    Platform save(Platform platform);
}
