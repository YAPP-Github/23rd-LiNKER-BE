package com.imlinker.domain.schedules;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.domain.schedules.model.Schedules;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleSearchService {

    private final ScheduleReader scheduleReader;
    private final ScheduleParticipantReader scheduleParticipantReader;

    public ScheduleDetail getScheduleDetails(Long userId, Long scheduleId) {
        Schedules schedules = scheduleReader.getSchedule(userId, scheduleId);
        List<Contacts> participants = scheduleParticipantReader.findScheduleParticipants(scheduleId);

        return new ScheduleDetail(
                schedules.id(),
                schedules.title(),
                schedules.category(),
                schedules.color(),
                schedules.description(),
                schedules.startDateTime(),
                schedules.endDateTime(),
                participants);
    }
}
