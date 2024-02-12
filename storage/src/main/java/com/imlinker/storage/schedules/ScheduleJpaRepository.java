package com.imlinker.storage.schedules;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findAllByUserIdAndStartDateTimeAfter(
            Long userId, LocalDateTime baseDateTime);

    Optional<ScheduleEntity> findByIdAndUserId(Long id, Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ScheduleEntity s where s.id = :id and s.userId = :userId")
    void deleteAllByIdAndUserId(Long id, Long userId);
}
