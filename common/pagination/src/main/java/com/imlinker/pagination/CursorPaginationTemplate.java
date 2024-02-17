package com.imlinker.pagination;

import java.util.List;
import java.util.function.Function;

public class CursorPaginationTemplate {
    public static <T extends CursorExtractable> CursorPaginationResult<T> execute(
            int size, Function<Integer, List<T>> query) {
        List<T> data = query.apply(size + 1);
        boolean hasNext = data.size() == size + 1;
        Long nextCursor = hasNext ? data.get(size).getId() : null;

        return new CursorPaginationResult<>(
                data.subList(0, Math.min(data.size(), size)), nextCursor, hasNext);
    }
}
