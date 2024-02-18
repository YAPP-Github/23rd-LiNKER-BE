package com.imlinker.domain.news;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.model.News;
import com.imlinker.enums.OperationResult;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsUpdater newsUpdater;
    private final NewsReader newsReader;
    private final TagSpecificNewsListFactory tagSpecificNewsListFactory;

    public Boolean checkDuplicateNews(String newsUrl) {
        Optional<News> news = newsReader.findByNewsUrl(newsUrl);
        return news.isPresent();
    }

    public List<TagSpecificNews> findAllByTagIdWithCursor(
            int size, List<Long> tagIds, Long cursorId) {
        CursorPaginationResult<News> selectedTagsNewsList =
                newsReader.findAllByTagIdWithCursor(size, tagIds, cursorId);

        return tagSpecificNewsListFactory.build(tagIds, selectedTagsNewsList);
    }

    @Transactional
    public OperationResult create(
            Long tagId, String title, String thumbnailUrl, URL newsUrl, String newsProvider) {
        log.info(
                "[뉴스 생성] tagId: {}, title: {}, thumbnailUrl: {}, newsUrl: {}, newsProvider: {}",
                tagId,
                title,
                thumbnailUrl,
                newsUrl,
                newsProvider);
        newsUpdater.create(tagId, title, thumbnailUrl, newsUrl, newsProvider);
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult create(List<CreateNewsParam> createNewsParams) {
        newsUpdater.create(createNewsParams);
        return OperationResult.SUCCESS;
    }
}
