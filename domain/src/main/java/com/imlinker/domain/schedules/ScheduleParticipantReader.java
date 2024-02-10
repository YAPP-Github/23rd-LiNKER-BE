package com.imlinker.domain.schedules;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleParticipantReader {
    private final ScheduleParticipantRepository scheduleParticipantRepository;

    public List<Contacts> findScheduleParticipants(Long scheduleId) {
        return scheduleParticipantRepository.findAllByScheduleId(scheduleId);
    }

    public LocalDate findContactRecentMeetingTime(Long contactId) {
        return scheduleParticipantRepository.findRecentMeetingTimeByContactId(contactId);
    }
}
