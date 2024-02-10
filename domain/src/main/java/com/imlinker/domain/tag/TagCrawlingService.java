package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.TagCrawling;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagCrawlingService {
    private final TagCrawlingReader tagCrawlingReader;

    public Optional<TagCrawling> getTagCrawling(Long tagId, String platform) {
        return tagCrawlingReader.findByTagIdAndPlatform(tagId, platform);
    }
}
