package com.imlinker.domain.schedules.model;

import com.imlinker.domain.schedules.model.query.SearchNearTermScheduleQueryCondition;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    List<Schedules> searchNearTermSchedules(SearchNearTermScheduleQueryCondition condition);

    Optional<Schedules> findByIdAndUserId(Long id, Long userId);

    Schedules save(Schedules schedules);

    void deleteByIdAndUserId(Long id, Long userId);
}
