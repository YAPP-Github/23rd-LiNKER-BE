package com.imlinker.coreapi.core.schedules.request;

import com.imlinker.domain.schedules.UpdateScheduleParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record UpdateScheduleRequest(
        @NotBlank @Schema(description = "제목") String title,
        @Schema(description = "설명") String description,
        @NotNull @Schema(description = "시작 날짜") LocalDateTime startDateTime,
        @NotNull @Schema(description = "종료 날짜") LocalDateTime endDateTime,
        @NotNull @Schema(description = "카테고리") String category,
        @NotNull @Schema(description = "색깔") String color,
        @NotEmpty(message = "일정 참여자가 충분하지 않습니다.") @Schema(description = "연락처 식별자 List")
                List<Long> contactIds) {

    public UpdateScheduleParam toParam(Long userId, Long scheduleId) {
        return new UpdateScheduleParam(
                userId,
                scheduleId,
                title,
                description,
                startDateTime,
                endDateTime,
                category,
                color,
                contactIds);
    }
}
