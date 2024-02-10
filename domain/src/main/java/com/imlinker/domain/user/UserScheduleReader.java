package com.imlinker.domain.user;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserScheduleReader {

    private final ScheduleRepository scheduleRepository;

    List<Schedules> findUpcomingSchedules(Long userId, LocalDateTime baseTime) {
        return scheduleRepository.findAllUpcomingSchedules(userId, baseTime);
    }
}
