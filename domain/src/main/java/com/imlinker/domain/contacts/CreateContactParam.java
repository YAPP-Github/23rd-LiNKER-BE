package com.imlinker.domain.contacts;

import com.imlinker.domain.tag.Tag;
import java.util.List;

public record CreateContactParam(
        String name,
        Long userId,
        String profileImgUrl,
        String job,
        String association,
        String description,
        List<Tag> interests) {}
