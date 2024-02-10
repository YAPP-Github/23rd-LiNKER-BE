package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.enums.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleUpdater scheduleUpdater;
    private final ScheduleParticipantUpdater scheduleParticipantUpdater;

    @Transactional
    public OperationResult create(CreateScheduleParam param) {
        Schedules schedule =
                scheduleUpdater.create(
                        param.userId(),
                        param.title(),
                        param.category(),
                        param.color(),
                        param.description(),
                        param.startDateTime(),
                        param.endDateTime());

        scheduleParticipantUpdater.create(schedule, param.contactIds());
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult update(UpdateScheduleParam param) {
        Schedules schedule =
                scheduleUpdater.update(
                        param.scheduleId(),
                        param.userId(),
                        param.title(),
                        param.category(),
                        param.color(),
                        param.description(),
                        param.startDateTime(),
                        param.endDateTime());
        scheduleParticipantUpdater.update(schedule, param.contactIds());
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult delete(Long userId, Long scheduleId) {
        scheduleUpdater.delete(userId, scheduleId);
        scheduleParticipantUpdater.delete(scheduleId);
        return OperationResult.SUCCESS;
    }
}
