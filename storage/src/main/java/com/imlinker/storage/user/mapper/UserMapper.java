package com.imlinker.storage.user.mapper;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.user.model.User;
import com.imlinker.storage.user.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toModel(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .oAuthVendor(OAuthVendor.of(entity.getOauthVendor()))
                .oAuthIdentifier(entity.getOauthIdentifier())
                .name(entity.getName())
                .profileImgUrl(URL.of(entity.getProfileImgUrl()))
                .email(Email.of(entity.getEmail()))
                .refreshToken(entity.getRefreshToken())
                .build();
    }

    public static UserEntity toEntity(User model) {
        return UserEntity.builder()
                .id(model.id())
                .oauthVendor(model.oAuthVendor().name())
                .oauthIdentifier(model.oAuthIdentifier())
                .name(model.name())
                .profileImgUrl(model.profileImgUrl().getValue())
                .email(model.email().getValue())
                .refreshToken(model.refreshToken())
                .build();
    }
}
