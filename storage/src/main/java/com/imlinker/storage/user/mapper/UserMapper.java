package com.imlinker.storage.user.mapper;

import com.imlinker.domain.auth.OAuthVendor;
import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.user.User;
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
                .build();
    }

    public static UserEntity toEntity(User model) {
        return UserEntity.builder()
                .id(model.getId())
                .oauthVendor(model.getOAuthVendor().name())
                .oauthIdentifier(model.getOAuthIdentifier())
                .name(model.getName())
                .profileImgUrl(model.getProfileImgUrl().getValue())
                .email(model.getEmail().getValue())
                .build();
    }
}
