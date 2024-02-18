package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.TagReader;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagSpecificNewsListFactory {

    private final TagReader tagReader;

    public List<TagSpecificNews> build(
            List<Long> tagIds, CursorPaginationResult<News> selectedTagsNewsList) {
        List<Tag> allTags = tagReader.findAll();
        ArrayList<TagSpecificNews> tagSpecificNewsList = new ArrayList<>();

        // tag가 비어있다면 ALL을 의미한다.
        List<Tag> selectedTags =
                tagIds.isEmpty()
                        ? allTags
                        : allTags.stream().filter(tag -> tagIds.contains(tag.getId())).toList();
        TagSpecificNews seletedTagSpecificNews =
                new TagSpecificNews(selectedTags, selectedTagsNewsList);
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
}
