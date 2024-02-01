package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.tag.model.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagReader {
    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
