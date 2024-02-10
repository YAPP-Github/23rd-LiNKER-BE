package com.imlinker.storage.news.mapper;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.model.News;
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
                .thumbnailUrl(entity.getThumbnailUrl())
                .newsUrl(URL.of(entity.getNewsUrl()))
                .newsProvider(entity.getNewsProvider())
                .build();
    }

    public static NewsEntity toEntity(News model) {
        return NewsEntity.builder()
                .id(model.getId())
                .tagId(model.getTagId())
                .title(model.getTitle())
                .thumbnailUrl(model.getThumbnailUrl())
                .newsProvider(model.getNewsProvider())
                .newsUrl(model.getNewsUrl().getValue())
                .build();
    }
}
