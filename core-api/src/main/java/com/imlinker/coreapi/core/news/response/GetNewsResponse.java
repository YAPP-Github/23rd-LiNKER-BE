package com.imlinker.coreapi.core.news.response;

import com.imlinker.domain.common.Tag;
import java.util.List;

public class GetNewsResponse {
    public record Entry(Tag tag, List<SimpleNews> news) {}

    public record SimpleNews(Long id, String title, String newsProvider, String thumbnailUrl) {}
}
