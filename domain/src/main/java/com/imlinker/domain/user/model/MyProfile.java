package com.imlinker.domain.user.model;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;
import lombok.Builder;

@Builder
public record MyProfile(
        Long id,
        String name,
        URL profileImgUrl,
        Email email,
        List<Tag> interests,
        int contactsNum,
        int scheduleNum) {}
