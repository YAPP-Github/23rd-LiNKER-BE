package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.ScheduleParticipantReader;
import com.imlinker.domain.schedules.ScheduleReader;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.domain.schedules.model.Schedules;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsScheduleSearchManager {

    private final ScheduleReader scheduleReader;
    private final ScheduleParticipantReader scheduleParticipantReader;

    public List<ScheduleDetail> searchContactsNearTermSchedules(
            int size, Contacts contacts, boolean isUpcoming, LocalDateTime baseDateTime) {
        List<Schedules> schedules =
                scheduleReader.findContactNearTermSchedules(size, contacts, isUpcoming, baseDateTime);
        return schedules.stream()
                .map(
                        schedule -> {
                            List<Contacts> participants =
                                    scheduleParticipantReader.findScheduleParticipants(schedule.id());
                            return ScheduleDetail.of(schedule, participants);
                        })
                .toList();
    }
}
