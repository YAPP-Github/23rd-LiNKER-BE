package com.imlinker.storage.user;

import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.domain.user.model.UserInterestRepository;
import com.imlinker.storage.user.mapper.UserInterestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInterestAdaptor implements UserInterestRepository {

    private final UserInterestJpaRepository repo;

    @Override
    public List<UserInterest> findAllByUserId(Long userId) {
        List<UserInterestEntity> entities = repo.findAllByUserId(userId);

        return entities.stream().map(UserInterestMapper::toModel).toList();
    }

    @Override
    public UserInterest save(UserInterest userInterest) {
        UserInterestEntity entity = repo.save(UserInterestMapper.toEntity(userInterest));
        return UserInterestMapper.toModel(entity);
    }
}
