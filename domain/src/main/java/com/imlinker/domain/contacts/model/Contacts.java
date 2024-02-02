package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.URL;
import lombok.Builder;

@Builder
public record Contacts(
        Long id,
        Long userId,
        String name,
        String job,
        String association,
        URL profileImgUrl,
        String description) {}
