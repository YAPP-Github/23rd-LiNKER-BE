package com.imlinker.domain.news;

import com.imlinker.domain.common.URL;
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

    private URL thumbnailImgUrl;

    private String company;
}
