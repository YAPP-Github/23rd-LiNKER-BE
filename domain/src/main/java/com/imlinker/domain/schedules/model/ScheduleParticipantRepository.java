package com.imlinker.domain.schedules.model;

import com.imlinker.domain.contacts.model.Contacts;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleParticipantRepository {
    LocalDate findRecentMeetingTimeByContactId(Long contactId);

    List<ScheduleParticipant> saveAll(List<ScheduleParticipant> scheduleParticipants);

    List<Contacts> findAllByContactId(Long contactId);

    List<Contacts> findAllByScheduleId(Long scheduleId);

    void deleteAllByScheduleId(Long scheduleId);
}
