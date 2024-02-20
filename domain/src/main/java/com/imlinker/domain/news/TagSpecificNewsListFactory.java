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
        List<Tag> selectedTags = tagReader.findAll();
        ArrayList<TagSpecificNews> tagSpecificNewsList = new ArrayList<>();

        TagSpecificNews seletedTagSpecificNews =
                new TagSpecificNews(selectedTags, selectedTagsNewsList);
        tagSpecificNewsList.add(seletedTagSpecificNews);

        if (tagIds.size() == 1) {
            // 단일 Tag 조회
            tagSpecificNewsList.addAll(
                    selectedTags.stream()
                            .filter(tag -> !tag.equals(selectedTags.get(0)))
                            .map(tag -> new TagSpecificNews(List.of(tag), CursorPaginationResult.initial()))
                            .toList());
        } else {
            // 복합 Tag 조회
            tagSpecificNewsList.addAll(
                    selectedTags.stream()
                            .map(tag -> new TagSpecificNews(List.of(tag), CursorPaginationResult.initial()))
                            .toList());
        }

        return tagSpecificNewsList;
    }
}
