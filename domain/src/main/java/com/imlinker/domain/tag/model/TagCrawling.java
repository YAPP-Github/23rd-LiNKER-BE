package com.imlinker.domain.tag.model;

import lombok.Builder;

@Builder
public record TagCrawling(Long id, Long tagId, String platform, String section) {}
