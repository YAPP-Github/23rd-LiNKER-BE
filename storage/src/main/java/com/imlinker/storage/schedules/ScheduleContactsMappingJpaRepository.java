package com.imlinker.storage.schedules;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleContactsMappingJpaRepository
        extends JpaRepository<ScheduleContactsMappingEntity, Long> {
    List<ScheduleContactsMappingEntity> findAllByContactId(Long contactId);

    List<ScheduleContactsMappingEntity> findAllByScheduleId(Long scheduleId);
}
