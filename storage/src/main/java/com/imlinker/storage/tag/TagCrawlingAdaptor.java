package com.imlinker.storage.tag;

import com.imlinker.domain.tag.model.TagCrawling;
import com.imlinker.domain.tag.model.TagCrawlingRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.tag.mapper.TagCrawlingMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagCrawlingAdaptor implements TagCrawlingRepository {
    private final TagCrawlingJpaRepository repo;

    @Override
    public TagCrawling findById(Long id) {
        TagCrawlingEntity entity =
                repo.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.TAG_NOT_FOUND));
        return TagCrawlingMapper.toModel(entity);
    }

    @Override
    public Optional<TagCrawling> findByTagIdAndPlatform(Long tagId, String name) {
        return repo.findByTagIdAndPlatform(tagId, name).map(TagCrawlingMapper::toModel);
    }

    @Override
    public TagCrawling save(TagCrawling tagCrawling) {
        TagCrawlingEntity entity = repo.save(TagCrawlingMapper.toEntity(tagCrawling));
        return TagCrawlingMapper.toModel(entity);
    }
}
