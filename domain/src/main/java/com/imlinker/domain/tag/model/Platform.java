package com.imlinker.domain.tag.model;

import lombok.Builder;

@Builder
public record Platform(Long id, String name, String section) {}
