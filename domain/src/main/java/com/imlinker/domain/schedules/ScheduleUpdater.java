package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ScheduleUpdater {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleParticipantRepository scheduleParticipantRepository;

    @Transactional
    public Schedules create(
            Long userId,
            String title,
            String category,
            String color,
            String description,
            List<Long> contactsId,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        Schedules schedules =
                scheduleRepository.save(
                        new Schedules(
                                null, userId, title, category, color, description, startDateTime, endDateTime));

        scheduleParticipantRepository.saveAll(
                contactsId.stream()
                        .map(
                                contactId ->
                                        new ScheduleParticipant(
                                                schedules.id(),
                                                contactId,
                                                schedules.startDateTime(),
                                                schedules.endDateTime()))
                        .toList());
        return schedules;
    }

    @Transactional
    public void delete(Long userId, Long scheduleId) {

        Schedules schedule =
                scheduleRepository
                        .findByIdAndUserId(scheduleId, userId)
                        .orElseThrow(() -> new ApplicationException(ErrorType.SCHEDULE_NOT_FOUND));

        scheduleRepository.deleteByIdAndUserId(schedule.id(), schedule.userId());
        scheduleParticipantRepository.deleteAllByScheduleId(schedule.id());
    }
}
