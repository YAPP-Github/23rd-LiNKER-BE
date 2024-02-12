package com.imlinker.domain.news.model.query;

import java.util.List;

public record NewsPaginationQueryCondition(int size, List<Long> tagIds, Long cursorId) {}
