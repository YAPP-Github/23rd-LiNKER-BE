package com.imlinker.coreapi.core.schedules.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchSchedulesResponse {

    @Schema(description = "일정 List")
    public record Schedules(List<SimpleSchedule> schedules) {}

    @Schema(description = "일정")
    public record SimpleSchedule(
            @Schema(description = "삭별자") Long scheduleId,
            @Schema(description = "제목") String title,
            @Schema(description = "시작 날짜") LocalDateTime startDateTime,
            @Schema(description = "종료 날짜") LocalDateTime endDateTime,
            @Schema(description = "색") String color,
            @Schema(description = "설명") String description,
            @Schema(description = "연락처 List") List<SimpleContact> contacts) {}

    @Schema(description = "연락처")
    public record SimpleContact(
            @Schema(description = "식별자") Long contactId,
            @Schema(description = "이름") String name,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl) {}
}
