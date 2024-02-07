package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleUpdater {
    private final ScheduleRepository scheduleRepository;
}
