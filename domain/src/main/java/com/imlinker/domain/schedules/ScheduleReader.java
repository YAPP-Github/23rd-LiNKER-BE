package com.imlinker.domain.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.domain.schedules.model.query.SearchNearTermScheduleQueryCondition;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleReader {

    private final ScheduleRepository scheduleRepository;

    public Schedules getSchedule(Long userId, Long scheduleId) {
        return scheduleRepository
                .findByIdAndUserId(scheduleId, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.SCHEDULE_NOT_FOUND));
    }

    public List<Schedules> findNearTermSchedules(
            int size, Long userId, boolean isUpcoming, LocalDateTime baseDateTime) {
        SearchNearTermScheduleQueryCondition condition =
                new SearchNearTermScheduleQueryCondition(size, userId, isUpcoming, baseDateTime);
        return scheduleRepository.searchNearTermSchedules(condition);
    }
}
