package com.imlinker.storage.user;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.user.model.User;
import com.imlinker.domain.user.model.UserRepository;
import com.imlinker.storage.user.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAdaptor implements UserRepository {

    private final UserJpaRepository repo;

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entity = repo.findById(id);
        return entity.map(UserMapper::toModel);
    }

    @Override
    public Optional<User> findByOAuthVendorAndOAuthIdentifier(
            OAuthVendor oAuthVendor, String oAuthIdentifier) {
        return repo.findByOauthVendorAndOauthIdentifier(oAuthVendor.name(), oAuthIdentifier)
                .map(UserMapper::toModel);
    }

    @Override
    public User save(User user) {
        UserEntity entity = repo.save(UserMapper.toEntity(user));
        return UserMapper.toModel(entity);
    }
}
