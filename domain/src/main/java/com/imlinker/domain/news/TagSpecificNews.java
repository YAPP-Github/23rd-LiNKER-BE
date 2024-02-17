package com.imlinker.domain.news;

import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.List;

public record TagSpecificNews(List<Tag> tags, CursorPaginationResult<News> newsList) {}
