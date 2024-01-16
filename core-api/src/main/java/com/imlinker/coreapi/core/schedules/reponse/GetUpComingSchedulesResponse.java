package com.imlinker.coreapi.core.schedules.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUpComingSchedulesResponse {

    @Schema(description = "일정 List")
    public record Schedules(List<SimpleSchedule> schedules) {}

    @Schema(description = "일정")
    public record SimpleSchedule(
            @Schema(description = "식별자") Long scheduleId,
            @Schema(description = "제목") String title,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl,
            @Schema(description = "시작 날짜") LocalDateTime startDateTime,
            @Schema(description = "종료 날짜") LocalDateTime endDateTime) {}
}
