package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleReader {

    private final ScheduleRepository scheduleRepository;

    public Schedules getSchedule(Long userId, Long scheduleId) {
        return scheduleRepository
                .findByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.SCHEDULE_NOT_FOUND));
    }
}
