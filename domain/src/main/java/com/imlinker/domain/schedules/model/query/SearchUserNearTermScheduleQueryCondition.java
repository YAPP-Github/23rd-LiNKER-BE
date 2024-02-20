package com.imlinker.domain.schedules.model.query;

import java.time.LocalDateTime;

public record SearchUserNearTermScheduleQueryCondition(
        int size, Long userId, boolean isUpcoming, LocalDateTime baseDateTime) {}
