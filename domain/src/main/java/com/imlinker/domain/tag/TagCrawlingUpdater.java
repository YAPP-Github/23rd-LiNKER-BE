package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.TagCrawling;
import com.imlinker.domain.tag.model.TagCrawlingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagCrawlingUpdater {
    private final TagCrawlingRepository tagCrawlingRepository;

    public TagCrawling create(Long tagId, String platform, String section) {
        return tagCrawlingRepository.save(new TagCrawling(null, tagId, platform, section));
    }
}
