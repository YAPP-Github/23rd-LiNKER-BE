package com.imlinker.storage.user.mapper;

import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.storage.user.UserInterestEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInterestMapper {

    public static UserInterest toModel(UserInterestEntity entity) {
        return new UserInterest(entity.getId(), entity.getTagId(), entity.getUserId());
    }

    public static UserInterestEntity toEntity(UserInterest model) {
        return new UserInterestEntity(model.id(), model.userId(), model.tagId());
    }
}
