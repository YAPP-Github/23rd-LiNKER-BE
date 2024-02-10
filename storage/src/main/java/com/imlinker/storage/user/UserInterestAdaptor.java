package com.imlinker.storage.user;

import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.domain.user.model.UserInterestRepository;
import com.imlinker.storage.tag.TagEntity;
import com.imlinker.storage.tag.mapper.TagMapper;
import com.imlinker.storage.user.mapper.UserInterestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInterestAdaptor implements UserInterestRepository {

    private final UserInterestJdbcQueryRepository queryRepo;
    private final UserInterestJpaRepository commandRepo;

    @Override
    public List<Tag> findAllByUserId(Long userId) {
        List<TagEntity> entities = queryRepo.findAllByUserId(userId);
        return entities.stream().map(TagMapper::toModel).toList();
    }

    @Override
    public UserInterest save(UserInterest userInterest) {
        UserInterestEntity entity = commandRepo.save(UserInterestMapper.toEntity(userInterest));
        return UserInterestMapper.toModel(entity);
    }

    @Override
    public List<UserInterest> saveAll(List<UserInterest> userInterests) {
        List<UserInterestEntity> entities =
                userInterests.stream().map(UserInterestMapper::toEntity).toList();
        return commandRepo.saveAll(entities).stream().map(UserInterestMapper::toModel).toList();
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        commandRepo.deleteAllByUserId(userId);
    }
}
