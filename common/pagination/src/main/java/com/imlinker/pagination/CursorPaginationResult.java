package com.imlinker.pagination;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public record CursorPaginationResult<T>(List<T> data, Long nextCursor, boolean hasNext) {

    public <R> CursorPaginationResult<R> transform(Function<T, R> transformer) {
        return new CursorPaginationResult<>(
                data.stream().map(transformer).toList(), nextCursor, hasNext);
    }

    public static <T> CursorPaginationResult<T> initial() {
        return new CursorPaginationResult<>(Collections.emptyList(), null, true);
    }
}
