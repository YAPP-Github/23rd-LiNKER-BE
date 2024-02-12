package com.imlinker.domain.news.model.query;

public record NewsPaginationQueryCondition(int size, Long tagId, Long cursorId) {}
