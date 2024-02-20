package com.imlinker.storage.schedules;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchUserNearTermScheduleQueryCondition;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleJdbcQueryRepositoryTest {

    @Autowired private ScheduleJdbcQueryRepository scheduleJdbcQueryRepository;

    @Test
    @DisplayName("현재 시점과 가까운 일정을 가져올 수 있다")
    public void findNearTermSchedulesTest() {
        assertDoesNotThrow(
                () -> {
                    SearchUserNearTermScheduleQueryCondition condition =
                            new SearchUserNearTermScheduleQueryCondition(1, 1L, true, LocalDateTime.now());
                    scheduleJdbcQueryRepository.findAllUserNearTermSchedulesWithLimit(condition);
                });
    }

    @Test
    @DisplayName("연락처와 날짜 범위를 통해서 일정을 가져올 수 있다")
    public void findByContactIdAndDateRangeTest() {
        assertDoesNotThrow(
                () -> {
                    SearchContactIdAndDateRangeScheduleQueryCondition condition =
                            new SearchContactIdAndDateRangeScheduleQueryCondition(
                                    1L, 1L, 1, LocalDateTime.now(), LocalDateTime.now());
                    scheduleJdbcQueryRepository.findByContactIdAndDateRange(condition);
                });
    }
}
