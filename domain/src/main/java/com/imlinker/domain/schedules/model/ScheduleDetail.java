package com.imlinker.domain.schedules.model;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.contacts.model.Contacts;
import java.time.LocalDateTime;
import java.util.List;

public record ScheduleDetail(
        Long id,
        String title,
        String category,
        String color,
        String description,
        URL profileImgUrl,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<Contacts> participants) {
    public static ScheduleDetail of(Schedules schedule, List<Contacts> participants) {
        return new ScheduleDetail(
                schedule.id(),
                schedule.title(),
                schedule.category(),
                schedule.color(),
                schedule.description(),
                participants.size() == 1 ? participants.get(0).profileImgUrl() : URL.empty(),
                schedule.startDateTime(),
                schedule.endDateTime(),
                participants);
    }
}
