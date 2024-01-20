package com.imlinker.storage.user.mapper;

import com.imlinker.domain.user.UserInterest;
import com.imlinker.storage.user.UserTagEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTagMapper {

    public static UserInterest toModel(UserTagEntity entity){
        return new UserInterest(
                entity.getId(),
                entity.getTagId(),
                entity.getUserId()
        );
    }

    public static UserTagEntity toEntity(UserInterest model){
        return new UserTagEntity(
                model.getId(),
                model.getUserId(),
                model.getTagId()
        );
    }
}
