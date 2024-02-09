package com.imlinker.domain.schedules;

import com.imlinker.enums.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleReader scheduleReader;
    private final ScheduleUpdater scheduleUpdater;

    public OperationResult create(CreateScheduleParam param) {
        scheduleUpdater.create(
                param.userId(),
                param.title(),
                param.category(),
                param.color(),
                param.description(),
                param.contactIds(),
                param.startDateTime(),
                param.endDateTime());

        return OperationResult.SUCCESS;
    }

    public OperationResult delete(Long userId, Long scheduleId) {
        scheduleUpdater.delete(userId, scheduleId);
        return OperationResult.SUCCESS;
    }
}
