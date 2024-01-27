package com.imlinker.domain.user.model;

import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.tag.Tag;
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
