package com.imlinker.storage.schedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findAllByUserIdAndStartDateTimeAfter(
            Long userId, LocalDateTime baseDateTime);

    Optional<ScheduleEntity> findByIdAndUserId(Long id, Long userId);

    void deleteAllByIdAndUserId(Long id, Long userId);
}
