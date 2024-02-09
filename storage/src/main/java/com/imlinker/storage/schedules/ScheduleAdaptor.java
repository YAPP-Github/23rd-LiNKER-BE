package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.storage.schedules.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleAdaptor implements ScheduleRepository {

    private final ScheduleJpaRepository repo;

    @Override
    public Schedules save(Schedules schedules) {
        ScheduleEntity entity = ScheduleMapper.toEntity(schedules);
        return ScheduleMapper.toModel(repo.save(entity));
    }
}
