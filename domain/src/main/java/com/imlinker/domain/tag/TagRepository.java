package com.imlinker.domain.tag;

import java.util.List;

public interface TagRepository {

    Tag findById(Long id);
    List<Tag> findAll();
    List<Tag> findAllByIdIn(List<Long> ids);
    Tag save(Tag tag);

}
