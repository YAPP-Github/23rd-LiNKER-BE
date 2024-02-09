package com.imlinker.domain.news.model;

import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    News findById(Long id);

    News findByTagId(Long tagId);

    Optional<News> findByNewsUrl(String newsUrl);

    News save(News news);

    List<News> saveAll(List<News> news);
}
