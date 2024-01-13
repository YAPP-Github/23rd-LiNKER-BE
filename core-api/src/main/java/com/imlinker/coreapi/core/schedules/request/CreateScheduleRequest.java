package com.imlinker.coreapi.core.schedules.request;

import java.time.LocalDateTime;
import java.util.List;

public record CreateScheduleRequest(
        String title,
        String description,
        LocalDateTime startTs,
        LocalDateTime endTs,
        String category,
        List<String> contactIds) {}
