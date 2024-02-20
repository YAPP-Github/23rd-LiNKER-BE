package com.imlinker.domain.schedules.model.query;

import java.time.LocalDateTime;

public record SearchContactNearTermScheduleQueryCondition(
        int size, Long contactsId, boolean isUpcoming, LocalDateTime baseDateTime) {}
