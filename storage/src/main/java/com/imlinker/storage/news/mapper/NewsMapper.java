package com.imlinker.storage.news.mapper;

import com.imlinker.domain.common.URL;
import com.imlinker.domain.news.News;
import com.imlinker.storage.news.NewsEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsMapper {
    public static News toModel(NewsEntity entity) {
        return News.builder()
                .id(entity.getId())
                .tagId(entity.getTagId())
                .title(entity.getTitle())
                .thumbnailUrl(URL.of(entity.getThumbnailUrl()))
                .newsProvider(entity.getNewsProvider())
                .build();
    }

    public static NewsEntity toEntity(News model) {
        return NewsEntity.builder()
                .id(model.id())
                .tagId(model.tagId())
                .title(model.title())
                .thumbnailUrl(model.thumbnailUrl().getValue())
                .newsProvider(model.newsProvider())
                .build();
    }
}
