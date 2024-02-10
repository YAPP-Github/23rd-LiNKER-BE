package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import com.imlinker.domain.schedules.model.Schedules;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleParticipantUpdater {
    private final ScheduleParticipantRepository scheduleParticipantRepository;

    public List<ScheduleParticipant> create(Schedules schedule, List<Long> contactsId) {
        return scheduleParticipantRepository.saveAll(
                contactsId.stream()
                        .map(
                                contactId ->
                                        new ScheduleParticipant(
                                                schedule.id(), contactId, schedule.startDateTime(), schedule.endDateTime()))
                        .toList());
    }

    public List<ScheduleParticipant> update(Schedules schedule, List<Long> contactsId) {
        scheduleParticipantRepository.deleteAllByScheduleId(schedule.id());
        return scheduleParticipantRepository.saveAll(
                contactsId.stream()
                        .map(
                                contactId ->
                                        new ScheduleParticipant(
                                                schedule.id(), contactId, schedule.startDateTime(), schedule.endDateTime()))
                        .toList());
    }

    public void delete(Long scheduleId) {
        scheduleParticipantRepository.deleteAllByScheduleId(scheduleId);
    }
}
