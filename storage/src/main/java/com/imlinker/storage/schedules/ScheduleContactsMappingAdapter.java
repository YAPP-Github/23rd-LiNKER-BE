package com.imlinker.storage.schedules;

import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import com.imlinker.storage.schedules.mapper.ScheduleContactMappingMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleContactsMappingAdapter implements ScheduleParticipantRepository {

    private final ScheduleContactsMappingJpaRepository repo;

    @Override
    public LocalDate findRecentMeetingTimeByContactId(Long contactId) {
        return repo.findTop1ByContactIdAndScheduleStartAtIsBeforeOrderByScheduleStartAtDesc(
                        contactId, LocalDateTime.now())
                .map(it -> it.getScheduleStartAt().toLocalDate())
                .orElse(null);
    }

    @Override
    public List<ScheduleParticipant> saveAll(List<ScheduleParticipant> scheduleParticipants) {
        List<ScheduleContactsMappingEntity> entities =
                repo.saveAll(
                        scheduleParticipants.stream()
                                .map(ScheduleContactMappingMapper::toEntity)
                                .collect(Collectors.toList()));
        return entities.stream()
                .map(ScheduleContactMappingMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleParticipant> findAllByContactId(Long contactId) {
        List<ScheduleContactsMappingEntity> entities = repo.findAllByContactId(contactId);
        return entities.stream()
                .map(ScheduleContactMappingMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleParticipant> findAllByScheduleId(Long scheduleId) {
        List<ScheduleContactsMappingEntity> entities = repo.findAllByScheduleId(scheduleId);
        return entities.stream()
                .map(ScheduleContactMappingMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllByScheduleId(Long scheduleId) {
        repo.deleteAllByScheduleId(scheduleId);
    }
}
