package com.imlinker.coreapi.core.my.request;

import java.util.List;

public record UpdateMyInfoRequest(
        String name,
        String profileImgUrl,
        String job,
        String association,
        String email,
        List<String> tags) {}
