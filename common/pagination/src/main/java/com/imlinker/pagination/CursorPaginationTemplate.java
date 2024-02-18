package com.imlinker.pagination;

import java.util.List;
import java.util.function.BiFunction;

public class CursorPaginationTemplate {
    public static <T extends CursorExtractable> CursorPaginationResult<T> execute(
            Long cursor, int size, BiFunction<Long, Integer, List<T>> query) {
        List<T> data = query.apply(cursor, size + 1);
        boolean hasNext = data.size() == size + 1;
        Long nextCursor = hasNext ? data.get(size).getId() : null;

        return new CursorPaginationResult<>(
                data.subList(0, Math.min(data.size(), size)), nextCursor, hasNext);
    }
}
