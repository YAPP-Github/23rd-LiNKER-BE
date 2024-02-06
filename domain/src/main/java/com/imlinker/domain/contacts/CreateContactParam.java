package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record CreateContactParam(
        String name,
        Long userId,
        String profileImgUrl,
        String job,
        PhoneNumber phoneNumber,
        String association,
        String description,
        List<Tag> interests) {}
