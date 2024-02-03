package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.Tag;
import java.util.List;

public record UpdateContactParam(
        Long id,
        String name,
        Long userId,
        URL profileImgUrl,
        String job,
        PhoneNumber phoneNumber,
        String association,
        String description,
        List<Tag> interests) {}
