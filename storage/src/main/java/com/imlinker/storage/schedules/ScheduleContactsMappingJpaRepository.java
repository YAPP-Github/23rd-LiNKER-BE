package com.imlinker.storage.schedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleContactsMappingJpaRepository
        extends JpaRepository<ScheduleContactsMappingEntity, Long> {

    Optional<ScheduleContactsMappingEntity>
            findTop1ByContactIdAndScheduleStartAtIsBeforeOrderByScheduleStartAtDesc(
                    Long contactId, LocalDateTime currentDateTime);

    List<ScheduleContactsMappingEntity> findAllByContactId(Long contactId);

    List<ScheduleContactsMappingEntity> findAllByScheduleId(Long scheduleId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ScheduleContactsMappingEntity scm where scm.scheduleId = :scheduleId")
    void deleteAllByScheduleId(Long scheduleId);
}
