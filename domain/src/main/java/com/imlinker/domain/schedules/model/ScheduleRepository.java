package com.imlinker.domain.schedules.model;

import java.util.Optional;

public interface ScheduleRepository {

    Optional<Schedules> findByIdAndUserId(Long id, Long userId);

    Schedules save(Schedules schedules);

    void deleteByIdAndUserId(Long id, Long userId);
}
