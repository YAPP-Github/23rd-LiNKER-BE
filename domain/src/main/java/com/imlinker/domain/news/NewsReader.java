package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.news.model.NewsRepository;
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
}
