package com.imlinker.domain.news;

import com.imlinker.domain.common.URL;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateNewsParam {
    private Long tagId;
    private String title;
    private String thumbnailUrl;
    private URL newsUrl;
    private String newsProvider;
}
