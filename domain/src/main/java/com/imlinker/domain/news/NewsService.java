package com.imlinker.domain.news;

import com.imlinker.domain.common.model.URL;
import com.imlinker.enums.OperationResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsUpdater newsUpdater;

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
