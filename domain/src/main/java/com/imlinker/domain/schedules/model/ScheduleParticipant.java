package com.imlinker.domain.schedules.model;

import java.time.LocalDateTime;

public record ScheduleParticipant(
        Long scheduleId, Long contactId, LocalDateTime scheduleStartAt, LocalDateTime scheduleEndAt) {}
