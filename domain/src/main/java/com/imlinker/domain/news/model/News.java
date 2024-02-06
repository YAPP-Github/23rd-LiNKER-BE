package com.imlinker.domain.news.model;

import com.imlinker.domain.common.model.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class News {

    private Long id;

    private Long tagId;

    private String title;

    private String thumbnailUrl;

    private URL newsUrl;

    private String newsProvider;
}
