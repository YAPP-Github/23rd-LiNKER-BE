package com.imlinker.coreapi.core.schedules.reponse;

import com.imlinker.domain.schedules.model.ScheduleDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public record GetScheduleResponse(
        @Schema(description = "삭별자") Long scheduleId,
        @Schema(description = "제목") String title,
        @Schema(description = "시작 날짜") LocalDateTime startDateTime,
        @Schema(description = "종료 날짜") LocalDateTime endDateTime,
        @Schema(description = "색") String color,
        @Schema(description = "설명") String description,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "연락처 List") List<SimpleContact> contacts) {

    public static GetScheduleResponse fromScheduleDetail(ScheduleDetail detail) {
        return new GetScheduleResponse(
                detail.id(),
                detail.title(),
                detail.startDateTime(),
                detail.endDateTime(),
                detail.color(),
                detail.description(),
                detail.profileImgUrl().getValue(),
                detail.participants().stream()
                        .map(
                                contact ->
                                        new SimpleContact(
                                                contact.id(), contact.name(), contact.profileImgUrl().getValue()))
                        .toList());
    }

    @Schema(description = "연락처")
    public record SimpleContact(
            @Schema(description = "식별자") Long contactId,
            @Schema(description = "이름") String name,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl) {}
}
