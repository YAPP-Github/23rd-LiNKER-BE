package com.imlinker.domain.schedules.model;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleParticipantRepository {
    LocalDate findRecentMeetingTimeByContactId(Long contactId);

    List<ScheduleParticipant> saveAll(List<ScheduleParticipant> scheduleParticipants);

    List<ScheduleParticipant> findAllByContactId(Long contactId);

    List<ScheduleParticipant> findAllByScheduleId(Long scheduleId);

    void deleteAllByScheduleId(Long scheduleId);
}
