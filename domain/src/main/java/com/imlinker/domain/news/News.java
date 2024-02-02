package com.imlinker.domain.news;

import com.imlinker.domain.common.URL;
import lombok.Builder;

@Builder
public record News(Long id, Long tagId, String title, URL thumbnailUrl, String newsProvider) {}
