package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ScheduleUpdater {
    private final ScheduleRepository scheduleRepository;

    public Schedules create(
            Long userId,
            String title,
            String category,
            String color,
            String description,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        return scheduleRepository.save(
                new Schedules(
                        null, userId, title, category, color, description, startDateTime, endDateTime));
    }

    @Transactional
    public Schedules update(
            Long id,
            Long userId,
            String title,
            String category,
            String color,
            String description,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {

        Schedules updatedSchedules =
                fetch(id, userId).update(title, category, color, description, startDateTime, endDateTime);

        return scheduleRepository.save(updatedSchedules);
    }

    @Transactional
    public void delete(Long scheduleId, Long userId) {
        Schedules schedule = fetch(scheduleId, userId);
        scheduleRepository.deleteByIdAndUserId(schedule.id(), schedule.userId());
    }

    private Schedules fetch(Long id, Long userId) {
        return scheduleRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.SCHEDULE_NOT_FOUND));
    }
}
