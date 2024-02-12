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

    public Tag findById(Long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> findAllByIdIn(List<Long> ids) {
        return tagRepository.findAllByIdIn(ids);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
