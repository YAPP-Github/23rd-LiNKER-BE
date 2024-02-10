package com.imlinker.domain.schedules;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.domain.schedules.model.Schedules;
import java.time.LocalDateTime;
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

        return ScheduleDetail.of(schedules, participants);
    }

    public List<ScheduleDetail> searchNearTermSchedules(
            int size, Long userId, boolean isUpcoming, LocalDateTime baseDateTime) {
        List<Schedules> schedules =
                scheduleReader.findNearTermSchedules(size, userId, isUpcoming, baseDateTime);
        return schedules.stream()
                .map(
                        schedule -> {
                            List<Contacts> participants =
                                    scheduleParticipantReader.findScheduleParticipants(schedule.id());
                            return ScheduleDetail.of(schedule, participants);
                        })
                .toList();
    }

    public List<ScheduleDetail> searchScheduleByContactAndDateRange(
            Long userId, Long contactId, int size, LocalDateTime from, LocalDateTime to) {
        List<Schedules> schedules =
                scheduleReader.findScheduleByContactIdAndDateRange(userId, contactId, size, from, to);

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
