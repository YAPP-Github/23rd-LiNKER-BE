package com.imlinker.coreapi.core.contacts.response;

import com.imlinker.domain.common.Tag;
import java.util.List;

public record GetContactResponse(
        String id, String name, String profileImgUrl, String job, String association, List<Tag> tags) {}
