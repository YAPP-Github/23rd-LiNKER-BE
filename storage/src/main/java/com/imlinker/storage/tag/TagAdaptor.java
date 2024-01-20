package com.imlinker.storage.tag;

import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.tag.TagRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.tag.mapper.TagMapper;
import com.imlinker.storage.user.UserTagEntity;
import com.imlinker.storage.user.mapper.UserTagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagAdaptor implements TagRepository {

    private final TagJpaRepository repo;
    @Override
    public Tag findById(Long id) {
        TagEntity entity = repo.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.TAG_NOT_FOUND));
        return TagMapper.toModel(entity);
    }

    @Override
    public List<Tag> findAll() {
        List<TagEntity> entities = repo.findAll();
        return entities.stream().map(TagMapper::toModel).toList();
    }

    @Override
    public List<Tag> findAllByIdIn(List<Long> ids) {
        List<TagEntity> entities = repo.findAllByIdIn(ids);
        return entities.stream().map(TagMapper::toModel).toList();
    }

    @Override
    public Tag save(Tag tag) {
        TagEntity entity = repo.save(TagMapper.toEntity(tag));
        return TagMapper.toModel(entity);
    }
}
