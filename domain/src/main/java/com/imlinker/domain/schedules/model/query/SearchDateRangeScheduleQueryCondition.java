package com.imlinker.domain.schedules.model.query;

import java.time.LocalDateTime;

public record SearchDateRangeScheduleQueryCondition(
        Long userId, int size, LocalDateTime from, LocalDateTime to) {}
