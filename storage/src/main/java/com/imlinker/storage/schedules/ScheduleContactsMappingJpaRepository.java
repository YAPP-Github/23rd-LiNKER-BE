package com.imlinker.storage.schedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleContactsMappingJpaRepository
        extends JpaRepository<ScheduleContactsMappingEntity, Long> {

    Optional<ScheduleContactsMappingEntity>
            findTop1ByContactIdAndScheduleStartAtIsBeforeOrderByScheduleStartAtDesc(
                    Long contactId, LocalDateTime currentDateTime);

    List<ScheduleContactsMappingEntity> findAllByContactId(Long contactId);

    List<ScheduleContactsMappingEntity> findAllByScheduleId(Long scheduleId);
}
