package com.imlinker.domain.schedules.model;

import java.util.List;

public interface ScheduleParticipantRepository {

    List<ScheduleParticipant> saveAll(List<ScheduleParticipant> scheduleParticipants);

    List<ScheduleParticipant> findAllByContactId(Long contactId);

    List<ScheduleParticipant> findAllByScheduleId(Long scheduleId);
}
