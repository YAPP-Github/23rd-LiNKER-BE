package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.news.model.NewsRepository;
import com.imlinker.domain.news.model.query.NewsPaginationQueryCondition;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsReader {
    private final NewsRepository newsRepository;

    public Optional<News> findByNewsUrl(String newsUrl) {
        return newsRepository.findByNewsUrl(newsUrl);
    }

    public CursorPaginationResult<News> findAllByTagIdWithCursor(
            int size, List<Long> tagIds, Long cursorId) {
        NewsPaginationQueryCondition condition =
                new NewsPaginationQueryCondition(size, tagIds, cursorId);
        return newsRepository.findAllByTagIdWithCursor(condition);
    }
}
