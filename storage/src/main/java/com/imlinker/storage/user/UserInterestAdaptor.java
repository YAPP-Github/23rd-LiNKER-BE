package com.imlinker.storage.user;

import com.imlinker.domain.tag.model.Tag;
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

    private final UserInterestJdbcQueryRepository jdbcRepo;
    private final UserInterestJpaRepository jpaRepo;

    @Override
    public List<Tag> findAllByUserId(Long userId) {
        List<TagEntity> entities = jdbcRepo.findAllByUserId(userId);
        return entities.stream().map(TagMapper::toModel).toList();
    }

    @Override
    public UserInterest save(UserInterest userInterest) {
        UserInterestEntity entity = jpaRepo.save(UserInterestMapper.toEntity(userInterest));
        return UserInterestMapper.toModel(entity);
    }

    @Override
    public List<UserInterest> saveAll(List<UserInterest> userInterests) {
        List<UserInterestEntity> entities =
                userInterests.stream().map(UserInterestMapper::toEntity).toList();
        return jpaRepo.saveAll(entities).stream().map(UserInterestMapper::toModel).toList();
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        jpaRepo.deleteAllByUserId(userId);
    }
}
