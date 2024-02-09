package com.imlinker.storage.tag;

import com.imlinker.domain.tag.model.Platform;
import com.imlinker.domain.tag.model.PlatformRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.tag.mapper.PlatformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlatformAdaptor implements PlatformRepository {
    private final PlatformJpaRepository repo;

    @Override
    public Platform findById(Long id) {
        PlatformEntity entity =
                repo.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.TAG_NOT_FOUND));
        return PlatformMapper.toModel(entity);
    }

    @Override
    public Platform save(Platform platform) {
        PlatformEntity entity = repo.save(PlatformMapper.toEntity(platform));
        return PlatformMapper.toModel(entity);
    }
}
