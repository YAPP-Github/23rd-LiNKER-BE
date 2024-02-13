package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record GetNewsParam(List<Tag> tags, List<News> news, Long nextCursor) {}
