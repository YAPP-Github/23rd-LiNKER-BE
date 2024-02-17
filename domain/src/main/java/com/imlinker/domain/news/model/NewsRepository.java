package com.imlinker.domain.news.model;

import com.imlinker.pagination.CursorPaginationResult;
import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    News findById(Long id);

    News findByTagId(Long tagId);

    List<News> findTop20ByTagIdOrderByCreatedAtDesc(Long tagId);

    List<News> findTop20ByTagIdInOrderByCreatedAtDesc(List<Long> tagIds);

    CursorPaginationResult<News> findAllByTagIdWithCursor(int size, List<Long> tagIds, Long cursorId);

    Optional<News> findByNewsUrl(String newsUrl);

    News save(News news);

    List<News> saveAll(List<News> news);
}
