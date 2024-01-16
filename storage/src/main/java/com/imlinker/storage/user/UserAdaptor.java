package com.imlinker.storage.user;

import com.imlinker.domain.user.User;
import com.imlinker.domain.user.UserRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserAdaptor implements UserRepository {

    private final UserJpaRepository repo;
    @Override
    public User findById(Long id) {
        UserEntity entity =  repo.findById(id).orElseThrow(() -> new ApplicationException(ErrorType.USER_NOT_FOUND));
        return UserMapper.toModel(entity);
    }

    @Override
    public User save(User user) {
        UserEntity entity = repo.save(UserMapper.toEntity(user));
        return UserMapper.toModel(entity);
    }
}
