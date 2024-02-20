package com.imlinker.domain.schedules;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchContactNearTermScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchUserNearTermScheduleQueryCondition;
import com.imlinker.domain.user.model.User;
import com.imlinker.domain.schedules.model.query.SearchDateRangeScheduleQueryCondition;
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

    public List<Schedules> findUserNearTermSchedules(
            int size, User user, boolean isUpcoming, LocalDateTime baseDateTime) {
        SearchUserNearTermScheduleQueryCondition condition =
                new SearchUserNearTermScheduleQueryCondition(size, user.id(), isUpcoming, baseDateTime);
        return scheduleRepository.findAllUserNearTermSchedules(condition);
    }

    public List<Schedules> findContactNearTermSchedules(
            int size, Contacts contacts, boolean isUpcoming, LocalDateTime baseDateTime) {
        SearchContactNearTermScheduleQueryCondition condition =
                new SearchContactNearTermScheduleQueryCondition(
                        size, contacts.id(), isUpcoming, baseDateTime);
        return scheduleRepository.findAllContactNearTermSchedules(condition);
    }

    public List<Schedules> findScheduleByContactIdAndDateRange(
            Long userId, Long contactId, int size, LocalDateTime from, LocalDateTime to) {
        SearchContactIdAndDateRangeScheduleQueryCondition condition =
                new SearchContactIdAndDateRangeScheduleQueryCondition(userId, contactId, size, from, to);

        return scheduleRepository.findByContactIdAndDateRange(condition);
    }

    public List<Schedules> findScheduleByDateRange(
            Long userId, int size, LocalDateTime from, LocalDateTime to) {
        SearchDateRangeScheduleQueryCondition condition =
                new SearchDateRangeScheduleQueryCondition(userId, size, from, to);

        return scheduleRepository.findByDateRange(condition);
    }
}
