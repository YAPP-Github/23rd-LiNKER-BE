package com.imlinker.domain.news.model;

import com.imlinker.domain.news.model.query.NewsPaginationQueryCondition;
import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    News findById(Long id);

    News findByTagId(Long tagId);

    List<News> findTop20ByTagIdOrderByCreatedAtDesc(Long tagId);

    List<News> findTop20ByTagIdInOrderByCreatedAtDesc(List<Long> tagIds);

    List<News> findAllByTagIdWithCursor(NewsPaginationQueryCondition condition);

    Optional<News> findByNewsUrl(String newsUrl);

    News save(News news);

    List<News> saveAll(List<News> news);
}
