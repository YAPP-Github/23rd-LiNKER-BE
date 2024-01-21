package com.imlinker.domain.news;

public interface NewsRepository {
    News findById(Long id);

    News findBytagId(Long tagId);

    News save(News news);
}
