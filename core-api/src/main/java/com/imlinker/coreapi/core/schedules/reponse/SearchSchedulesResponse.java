package com.imlinker.coreapi.core.schedules.reponse;

import java.time.LocalDateTime;
import java.util.List;

public class SearchSchedulesResponse {
    public record Schedules(List<SimpleSchedule> schedules) {}

    public record SimpleSchedule(
            String scheduleId,
            String title,
            LocalDateTime startTs,
            LocalDateTime endTs,
            List<SimpleContact> participants) {}

    public record SimpleContact(String contactId, String name, String profileImgUrl) {}
}
