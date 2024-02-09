package com.imlinker.domain.schedules.model;

import com.imlinker.domain.contacts.model.Contacts;
import java.time.LocalDateTime;
import java.util.List;

public record ScheduleDetail(
        Long id,
        String title,
        String category,
        String color,
        String description,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        List<Contacts> participants) {}
