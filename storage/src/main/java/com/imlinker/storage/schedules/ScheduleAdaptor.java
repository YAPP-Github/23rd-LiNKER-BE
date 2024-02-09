package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.ScheduleRepository;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.storage.schedules.mapper.ScheduleMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScheduleAdaptor implements ScheduleRepository {

    private final ScheduleJpaRepository repo;

    @Override
    public Optional<Schedules> findByIdAndUserId(Long id, Long userId) {
        return repo.findByIdAndUserId(id, userId).map(ScheduleMapper::toModel);
    }

    @Override
    public Schedules save(Schedules schedules) {
        ScheduleEntity entity = ScheduleMapper.toEntity(schedules);
        return ScheduleMapper.toModel(repo.save(entity));
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        repo.deleteAllByIdAndUserId(id, userId);
    }
}
