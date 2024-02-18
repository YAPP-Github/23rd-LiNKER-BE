package com.imlinker.domain.news.model;

import com.imlinker.domain.news.model.query.NewsPaginationQueryCondition;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    News findById(Long id);

    CursorPaginationResult<News> findAllByTagIdWithCursor(NewsPaginationQueryCondition condition);

    Optional<News> findByNewsUrl(String newsUrl);

    News save(News news);

    List<News> saveAll(List<News> news);
}
