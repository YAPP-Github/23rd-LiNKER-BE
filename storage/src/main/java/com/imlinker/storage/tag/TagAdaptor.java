package com.imlinker.storage.tag;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.tag.model.TagRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.localcache.LocalCache;
import com.imlinker.storage.tag.mapper.TagMapper;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagAdaptor implements TagRepository {

    private final TagJpaRepository repo;
    private final LocalCache<String, List<Tag>> allTagsCache =
            new LocalCache<>(1, Duration.ofMinutes(10));

    @Override
    public Tag findById(Long id) {
        TagEntity entity =
                repo.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.TAG_NOT_FOUND));
        return TagMapper.toModel(entity);
    }

    @Override
    public List<Tag> findAll() {
        return allTagsCache.getOrPut(
                "all", (k) -> repo.findAll().stream().map(TagMapper::toModel).toList());
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
