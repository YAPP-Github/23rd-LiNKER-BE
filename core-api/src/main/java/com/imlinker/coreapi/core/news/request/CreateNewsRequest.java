package com.imlinker.coreapi.core.news.request;

import com.imlinker.domain.common.URL;

public record CreateNewsRequest(
        Long tagId, String title, String thumbnailUrl, URL newsUrl, String newsProvider) {}
