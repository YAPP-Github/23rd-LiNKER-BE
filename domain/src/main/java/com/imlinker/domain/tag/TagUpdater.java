package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.tag.model.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagUpdater {
    private final TagRepository tagRepository;

    public Tag create(Long platformId, String name) {
        return tagRepository.save(new Tag(null, platformId, name));
    }
}
