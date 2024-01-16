package com.imlinker.coreapi.core.schedules.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public record CreateScheduleRequest(
        @Schema(description = "제목") String title,
        @Schema(description = "설명") String description,
        @Schema(description = "시작 날짜") LocalDateTime startDateTime,
        @Schema(description = "종료 날짜") LocalDateTime endTs,
        @Schema(description = "카테고리") String category,
        @Schema(description = "연락처 식별자 List") List<String> contactIds) {}
