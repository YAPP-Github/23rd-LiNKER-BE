package com.imlinker.domain.schedules.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Schedules(
        Long id,
        Long userId,
        String title,
        String category,
        String color,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime) {}
