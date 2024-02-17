package com.imlinker.storage.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.news.model.NewsRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.pagination.CursorPaginationResult;
import com.imlinker.pagination.CursorPaginationTemplate;
import com.imlinker.storage.news.mapper.NewsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NewsAdaptor implements NewsRepository {

    private final NewsJpaRepository newsJpaRepository;
    private final NewsJdbcQueryRepository newsJdbcQueryRepository;

    @Override
    public News findById(Long id) {
        NewsEntity newsEntity =
                newsJpaRepository
                        .findById(id)
                        .orElseThrow(() -> new ApplicationException(ErrorType.NEWS_NOT_FOUND));
        return NewsMapper.toModel(newsEntity);
    }

    @Override
    public News findByTagId(Long tagId) {
        NewsEntity newsEntity =
                newsJpaRepository
                        .findByTagId(tagId)
                        .orElseThrow(() -> new ApplicationException(ErrorType.NEWS_NOT_FOUND));
        return NewsMapper.toModel(newsEntity);
    }

    @Override
    public List<News> findTop20ByTagIdOrderByCreatedAtDesc(Long tagId) {
        return newsJpaRepository.findTop20ByTagIdOrderByCreatedAtDesc(tagId).stream()
                .map(NewsMapper::toModel)
                .toList();
    }

    @Override
    public List<News> findTop20ByTagIdInOrderByCreatedAtDesc(List<Long> tagIds) {
        return newsJpaRepository.findTop20ByTagIdInOrderByCreatedAtDesc(tagIds).stream()
                .map(NewsMapper::toModel)
                .toList();
    }

    @Override
    public CursorPaginationResult<News> findAllByTagIdWithCursor(
            int size, List<Long> tagIds, Long cursorId) {
        CursorPaginationResult<NewsEntity> result =
                CursorPaginationTemplate.execute(
                        size,
                        (limit) -> newsJdbcQueryRepository.findAllByTagIdWithCursor(limit, tagIds, cursorId));

        return result.transform(NewsMapper::toModel);
    }

    @Override
    public Optional<News> findByNewsUrl(String newsUrl) {
        return newsJpaRepository.findByNewsUrl(newsUrl).map(NewsMapper::toModel);
    }

    @Override
    public News save(News news) {
        NewsEntity newsEntity = newsJpaRepository.save(NewsMapper.toEntity(news));
        return NewsMapper.toModel(newsEntity);
    }

    @Override
    public List<News> saveAll(List<News> newsList) {
        List<NewsEntity> newsEntityList =
                newsJpaRepository.saveAll(newsList.stream().map(NewsMapper::toEntity).toList());
        return newsEntityList.stream().map(NewsMapper::toModel).toList();
    }
}
