package com.imlinker.coreapi.core.schedules.reponse;

import java.time.LocalDateTime;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetUpComingSchedulesResponse {
    public record Schedules(List<SimpleSchedule> schedules) {}

    public record SimpleSchedule(
            String scheduleId,
            String title,
            String profileImgUrl,
            LocalDateTime startTs,
            LocalDateTime endTs) {}
}
