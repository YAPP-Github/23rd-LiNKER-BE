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
        int participantsNum,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime) {

    public Schedules update(
            String title,
            String category,
            String color,
            String description,
            int participantsNum,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        return new Schedules(
                id,
                userId,
                title,
                category,
                color,
                description,
                participantsNum,
                startDateTime,
                endDateTime);
    }
}
