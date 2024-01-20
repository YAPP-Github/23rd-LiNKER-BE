package com.imlinker.storage.user;

import com.imlinker.domain.user.UserInterest;
import com.imlinker.domain.user.UserInterestRepository;
import com.imlinker.storage.user.mapper.UserTagMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInterestAdaptor implements UserInterestRepository {

    private final UserTagJpaRepository repo;

    @Override
    public List<UserInterest> findAllByUserId(Long userId) {
        List<UserTagEntity> entities = repo.findAllByUserId(userId);

        return entities.stream().map(UserTagMapper::toModel).toList();
    }

    @Override
    public UserInterest save(UserInterest userInterest) {
        UserTagEntity entity = repo.save(UserTagMapper.toEntity(userInterest));
        return UserTagMapper.toModel(entity);
    }
}
