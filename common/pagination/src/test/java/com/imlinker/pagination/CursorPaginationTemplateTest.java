package com.imlinker.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CursorPaginationTemplateTest {

    @Test
    @DisplayName("마지막 Pagination이라면, 다음 Cursor는 null이고, hasNext도 false여야 한다.")
    void cursorIsNullAndHasNextFalseWhenLastPaging() {
        CursorPaginationResult<CursorExtractable> actual =
                CursorPaginationTemplate.execute(1L, 20, (cursor, size) -> List.of(() -> 1L));

        assertThat(actual.hasNext()).isFalse();
        assertThat(actual.nextCursor()).isNull();
        assertThat(actual.data()).hasSize(1);
    }

    @Test
    @DisplayName("Pagination이 마지막이 아니라면 NextCursor는 null이 아니며, hasNext는 True이다.")
    void cursorIsNotNullAndHasNextIsTrueWhenNotLastPaging() {

        CursorPaginationResult<CursorExtractable> actual =
                CursorPaginationTemplate.execute(
                        1L, 3, (cursor, size) -> List.of(() -> 1L, () -> 2L, () -> 3L, () -> 4L));

        assertThat(actual.hasNext()).isTrue();
        assertThat(actual.nextCursor()).isEqualTo(4L);
        assertThat(actual.data()).hasSize(3);
    }

    @Test
    @DisplayName("Pagination이 마지막이 아니라면 Pagination의 NextCursor는 결과값 List에는 포함되지 않는다.")
    void nextCursorIsNotIncludedInData() {

        CursorPaginationResult<CursorExtractable> actual =
                CursorPaginationTemplate.execute(
                        1L, 3, (cursor, size) -> List.of(() -> 1L, () -> 2L, () -> 3L, () -> 4L));

        assertThat(actual.hasNext()).isTrue();
        assertThat(actual.nextCursor()).isEqualTo(4L);
        assertThat(actual.data()).hasSize(3);
    }
}
