package com.imlinker.storage.news;

import com.imlinker.domain.news.News;
import com.imlinker.domain.news.NewsRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.news.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NewsAdaptor implements NewsRepository {

    private final NewsJpaRepository newsJpaRepository;

    @Override
    public News findById(Long id) {
        NewsEntity newsEntity =
                newsJpaRepository
                        .findById(id)
                        .orElseThrow(() -> new ApplicationException(ErrorType.NEWS_NOT_FOUND));
        return NewsMapper.toModel(newsEntity);
    }

    @Override
    public News findBytagId(Long tagId) {
        NewsEntity newsEntity =
                newsJpaRepository
                        .findBytagId(tagId)
                        .orElseThrow(() -> new ApplicationException(ErrorType.NEWS_NOT_FOUND));
        return NewsMapper.toModel(newsEntity);
    }

    @Override
    public News save(News news) {
        NewsEntity newsEntity = newsJpaRepository.save(NewsMapper.toEntity(news));
        return NewsMapper.toModel(newsEntity);
    }
}
