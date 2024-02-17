package com.imlinker.domain.news;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.TagReader;
import com.imlinker.domain.tag.model.Tag;
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
    private final TagReader tagReader;

    public Boolean checkDuplicateNews(String newsUrl) {
        Optional<News> news = newsReader.findByNewsUrl(newsUrl);
        return news.isPresent();
    }

    /** TagId가 여러개이면 ALL 취급이다. (ALL이 여러개면 아닐 수 잇지만) */
    public List<TagSpecificNews> findAllByTagIdWithCursor(
            int size, List<Long> tagIds, Long cursorId) {

        List<Tag> allTags = tagReader.findAll();
        CursorPaginationResult<News> selectedTagsNewsList =
                newsReader.findAllByTagIdWithCursor(size, tagIds, cursorId);

        // tag가 비어있다면 ALL을 의미한다.
        List<Tag> selectedTags =
                tagIds.isEmpty()
                        ? allTags
                        : allTags.stream().filter(tag -> tagIds.contains(tag.getId())).toList();
        TagSpecificNews seletedTagSpecificNews =
                new TagSpecificNews(selectedTags, selectedTagsNewsList);

        // 선택된 Tag는 맨 앞에 위치한다.
        ArrayList<TagSpecificNews> tagSpecificNewsList = new ArrayList<>();
        tagSpecificNewsList.add(seletedTagSpecificNews);

        if (tagIds.size() == 1) {
            // 단일 Tag 조회
            tagSpecificNewsList.addAll(
                    allTags.stream()
                            .filter(tag -> !tag.equals(selectedTags.get(0)))
                            .map(tag -> new TagSpecificNews(List.of(tag), CursorPaginationResult.initial()))
                            .toList());
        } else {
            // 복합 Tag 조회
            tagSpecificNewsList.addAll(
                    allTags.stream()
                            .map(tag -> new TagSpecificNews(List.of(tag), CursorPaginationResult.initial()))
                            .toList());
        }

        return tagSpecificNewsList;
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
