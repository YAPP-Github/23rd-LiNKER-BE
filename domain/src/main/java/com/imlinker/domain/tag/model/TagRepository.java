package com.imlinker.domain.tag.model;

import java.util.List;

public interface TagRepository {

    Tag findById(Long id);

    List<Tag> findAll();

    List<Tag> findAllByIdIn(List<Long> ids);

    Tag save(Tag tag);
}
