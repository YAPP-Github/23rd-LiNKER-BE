package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.TagCrawling;
import com.imlinker.domain.tag.model.TagCrawlingRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagCrawlingReader {
    private final TagCrawlingRepository tagCrawlingRepository;

    public TagCrawling findById(Long id) {
        return tagCrawlingRepository.findById(id);
    }

    public Optional<TagCrawling> findByTagIdAndPlatform(Long tagId, String name) {
        return tagCrawlingRepository.findByTagIdAndPlatform(tagId, name);
    }
}
