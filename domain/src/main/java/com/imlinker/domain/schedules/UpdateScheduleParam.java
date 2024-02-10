package com.imlinker.domain.schedules;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateScheduleParam(
        Long userId,
        Long scheduleId,
        String title,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String category,
        String color,
        List<Long> contactIds) {}
