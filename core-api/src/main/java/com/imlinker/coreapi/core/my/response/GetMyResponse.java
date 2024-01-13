package com.imlinker.coreapi.core.my.response;

import java.util.List;

public record GetMyResponse(
        String name,
        String profileImgUrl,
        String job,
        String association,
        String email,
        List<String> tags,
        int contactsNum,
        int scheduleNum) {}
