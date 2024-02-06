package com.imlinker.domain.news;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.news.model.NewsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsUpdater {
    private final NewsRepository newsRepository;

    public News create(
            Long tagId, String title, String thumbnailUrl, URL newsUrl, String newsProvider) {
        return newsRepository.save(new News(null, tagId, title, thumbnailUrl, newsUrl, newsProvider));
    }

    public List<News> create(List<CreateNewsParam> createNewsParams) {
        List<News> newsList =
                createNewsParams.stream()
                        .map(
                                createNewsParam ->
                                        new News(
                                                null,
                                                createNewsParam.getTagId(),
                                                createNewsParam.getTitle(),
                                                createNewsParam.getThumbnailUrl(),
                                                createNewsParam.getNewsUrl(),
                                                createNewsParam.getNewsProvider()))
                        .toList();

        return newsRepository.saveAll(newsList);
    }
}
