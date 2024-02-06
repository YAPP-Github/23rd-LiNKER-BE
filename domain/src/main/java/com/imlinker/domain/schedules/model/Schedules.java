package com.imlinker.domain.schedules.model;

import java.time.LocalDateTime;

public record Schedules(
        Long id,
        Long userId,
        String title,
        String category,
        String color,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        LocalDateTime createdAt) {}
