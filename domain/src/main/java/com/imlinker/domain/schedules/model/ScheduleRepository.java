package com.imlinker.domain.schedules.model;

import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchNearTermScheduleQueryCondition;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    List<Schedules> findAllNearTermSchedules(SearchNearTermScheduleQueryCondition condition);

    List<Schedules> findByContactIdAndDateRange(
            SearchContactIdAndDateRangeScheduleQueryCondition condition);

    Optional<Schedules> findByIdAndUserId(Long id, Long userId);

    Schedules save(Schedules schedules);

    void deleteByIdAndUserId(Long id, Long userId);
}
