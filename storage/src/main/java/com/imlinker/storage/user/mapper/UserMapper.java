package com.imlinker.storage.user.mapper;

import com.imlinker.domain.common.Email;
import com.imlinker.domain.user.User;
import com.imlinker.storage.user.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(Email.of(entity.getEmail()))
                .build();
    }

    public static UserEntity toEntity(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .email(model.getEmail().getValue())
                .build();
    }
}
