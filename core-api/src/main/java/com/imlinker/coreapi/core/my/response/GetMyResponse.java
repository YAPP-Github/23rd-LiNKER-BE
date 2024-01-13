package com.imlinker.coreapi.core.my.response;

import com.imlinker.domain.common.Tag;
import java.util.List;

public record GetMyResponse(
        String name,
        String profileImgUrl,
        String job,
        String association,
        String email,
        List<Tag> tags,
        int contactsNum,
        int scheduleNum) {}
