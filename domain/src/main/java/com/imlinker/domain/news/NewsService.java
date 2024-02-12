package com.imlinker.domain.news;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.TagReader;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.enums.OperationResult;
import java.util.List;
import java.util.Optional;
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
    private final TagReader tagReader;

    public Boolean checkDuplicateNews(String newsUrl) {
        Optional<News> news = newsReader.findByNewsUrl(newsUrl);
        return news.isPresent();
    }

    public GetNewsParam findAllByTagIdWithCursor(int size, Long tagId, Long cursorId) {
        Tag tag = tagReader.findById(tagId);
        List<News> newsList = newsReader.findAllByTagIdWithCursor(size, tagId, cursorId);

        Long nextCursor = null;
        int curListSize = newsList.size();
        if (curListSize < size) {
            nextCursor = -1L;
        } else {
            nextCursor = newsList.get(curListSize - 1).getId();
        }

        return new GetNewsParam(tag, newsList, nextCursor);
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
