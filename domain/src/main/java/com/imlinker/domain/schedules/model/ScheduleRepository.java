package com.imlinker.domain.schedules.model;

import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchContactNearTermScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchUserNearTermScheduleQueryCondition;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    List<Schedules> findAllUpcomingSchedules(Long userId, LocalDateTime baseDateTime);

    List<Schedules> findAllUserNearTermSchedules(SearchUserNearTermScheduleQueryCondition condition);

    List<Schedules> findAllContactNearTermSchedules(
            SearchContactNearTermScheduleQueryCondition condition);

    List<Schedules> findByContactIdAndDateRange(
            SearchContactIdAndDateRangeScheduleQueryCondition condition);

    Optional<Schedules> findByIdAndUserId(Long id, Long userId);

    Schedules save(Schedules schedules);

    void deleteByIdAndUserId(Long id, Long userId);
}
