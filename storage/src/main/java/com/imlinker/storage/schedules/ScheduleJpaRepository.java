package com.imlinker.storage.schedules;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {
    Optional<ScheduleEntity> findByIdAndUserId(Long id, Long userId);

    void deleteAllByIdAndUserId(Long id, Long userId);
}
