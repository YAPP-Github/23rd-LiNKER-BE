package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record GetNewsParam(Tag tag, List<News> news, Long nextCursor) {}
