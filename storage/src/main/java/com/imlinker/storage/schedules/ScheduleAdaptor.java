package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.domain.schedules.model.query.SearchNearTermScheduleQueryCondition;
import com.imlinker.storage.schedules.mapper.ScheduleMapper;
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
    public List<Schedules> searchNearTermSchedules(SearchNearTermScheduleQueryCondition condition) {
        return jdbcRepo.findNearTermSchedules(condition).stream().map(ScheduleMapper::toModel).toList();
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
