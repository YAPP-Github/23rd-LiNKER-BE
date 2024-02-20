package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.domain.schedules.model.query.SearchContactIdAndDateRangeScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchContactNearTermScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchUserNearTermScheduleQueryCondition;
import com.imlinker.domain.schedules.model.query.SearchDateRangeScheduleQueryCondition;
import com.imlinker.storage.schedules.mapper.ScheduleMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleAdaptor implements ScheduleRepository {

    private final ScheduleJpaRepository jpaRepo;
    private final ScheduleJdbcQueryRepository jdbcRepo;

    @Override
    public List<Schedules> findAllUpcomingSchedules(Long userId, LocalDateTime baseDateTime) {
        return jpaRepo.findAllByUserIdAndStartDateTimeAfter(userId, baseDateTime).stream()
                .map(ScheduleMapper::toModel)
                .toList();
    }

    @Override
    public List<Schedules> findAllUserNearTermSchedules(
            SearchUserNearTermScheduleQueryCondition condition) {
        return jdbcRepo.findAllUserNearTermSchedulesWithLimit(condition).stream()
                .map(ScheduleMapper::toModel)
                .toList();
    }

    @Override
    public List<Schedules> findAllContactNearTermSchedules(
            SearchContactNearTermScheduleQueryCondition condition) {
        return jdbcRepo.findAllContactNearTermSchedulesWithLimit(condition).stream()
                .map(ScheduleMapper::toModel)
                .toList();
    }

    @Override
    public List<Schedules> findByContactIdAndDateRange(
            SearchContactIdAndDateRangeScheduleQueryCondition condition) {
        return jdbcRepo.findByContactIdAndDateRange(condition).stream()
                .map(ScheduleMapper::toModel)
                .toList();
    }

    @Override
    public List<Schedules> findByDateRange(SearchDateRangeScheduleQueryCondition condition) {
        return jdbcRepo.findByDateRange(condition).stream().map(ScheduleMapper::toModel).toList();
    }

    @Override
    public Optional<Schedules> findByIdAndUserId(Long id, Long userId) {
        return jpaRepo.findByIdAndUserId(id, userId).map(ScheduleMapper::toModel);
    }

    @Override
    public Schedules save(Schedules schedules) {
        ScheduleEntity entity = ScheduleMapper.toEntity(schedules);
        return ScheduleMapper.toModel(jpaRepo.save(entity));
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        jpaRepo.deleteAllByIdAndUserId(id, userId);
    }
}
