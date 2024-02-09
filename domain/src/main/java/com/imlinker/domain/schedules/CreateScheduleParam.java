package com.imlinker.domain.schedules;

import java.time.LocalDateTime;
import java.util.List;

public record CreateScheduleParam(
        Long userId,
        String title,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String category,
        String color,
        List<Long> contactIds) {}
