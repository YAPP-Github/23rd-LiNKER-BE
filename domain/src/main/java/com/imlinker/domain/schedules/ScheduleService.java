package com.imlinker.domain.schedules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleReader scheduleReader;
    private final ScheduleUpdater scheduleUpdater;
}
