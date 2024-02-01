package com.imlinker.domain.news.model;

import java.util.List;

public interface NewsRepository {
    News findById(Long id);

    News findBytagId(Long tagId);

    News save(News news);

    List<News> saveAll(List<News> news);
}
