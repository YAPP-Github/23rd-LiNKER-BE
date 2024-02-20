package com.imlinker.coreapi.core.contacts.response;

import com.imlinker.domain.schedules.model.ScheduleDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetContactsUpcomingScheduleResponse {

    @Schema(description = "일정 List")
    public record Schedules(
            List<com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse.SimpleSchedule>
                    schedules) {}

    @Schema(description = "일정")
    public record SimpleSchedule(
            @Schema(description = "식별자") Long scheduleId,
            @Schema(description = "제목") String title,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl,
            @Schema(description = "색") String color,
            @Schema(description = "카테고리") String category,
            @Schema(description = "시작 날짜") LocalDateTime startDateTime,
            @Schema(description = "종료 날짜") LocalDateTime endDateTime,
            @Schema(description = "연락처 List")
                    List<
                                    com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse
                                            .SimpleSchedule.SimpleContact>
                            contacts) {

        public static com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse
                        .SimpleSchedule
                fromScheduleDetail(ScheduleDetail detail) {
            return new com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse
                    .SimpleSchedule(
                    detail.id(),
                    detail.title(),
                    detail.profileImgUrl().getValue(),
                    detail.color(),
                    detail.category(),
                    detail.startDateTime(),
                    detail.endDateTime(),
                    detail.participants().stream()
                            .map(
                                    participant ->
                                            new com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse
                                                    .SimpleSchedule.SimpleContact(
                                                    participant.id(),
                                                    participant.name(),
                                                    participant.profileImgUrl().getValue()))
                            .toList());
        }

        @Schema(description = "연락처")
        public record SimpleContact(
                @Schema(description = "식별자") Long contactId,
                @Schema(description = "이름") String name,
                @Schema(description = "프로필 이미지 URL") String profileImgUrl) {}
    }
}
