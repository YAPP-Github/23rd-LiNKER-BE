package com.imlinker.domain.schedules.model.query;

import java.time.LocalDateTime;

public record SearchContactIdAndDateRangeScheduleQueryCondition(
        Long userId, Long contactId, int size, LocalDateTime from, LocalDateTime to) {}
