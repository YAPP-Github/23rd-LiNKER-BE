package com.imlinker.storage.schedules;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import com.imlinker.storage.contacts.mapper.ContactsMapper;
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

    private final ScheduleContactJdbcQueryRepository jdbcRepo;
    private final ScheduleContactsMappingJpaRepository jpaRepo;

    @Override
    public LocalDate findRecentMeetingTimeByContactId(Long contactId) {
        return jpaRepo
                .findTop1ByContactIdAndScheduleStartAtIsBeforeOrderByScheduleStartAtDesc(
                        contactId, LocalDateTime.now())
                .map(it -> it.getScheduleStartAt().toLocalDate())
                .orElse(null);
    }

    @Override
    public List<ScheduleParticipant> saveAll(List<ScheduleParticipant> scheduleParticipants) {
        List<ScheduleContactsMappingEntity> entities =
                jpaRepo.saveAll(
                        scheduleParticipants.stream()
                                .map(ScheduleContactMappingMapper::toEntity)
                                .collect(Collectors.toList()));
        return entities.stream()
                .map(ScheduleContactMappingMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contacts> findAllByContactId(Long contactId) {
        return jdbcRepo.findAllByContactId(contactId).stream().map(ContactsMapper::toModel).toList();
    }

    @Override
    public List<Contacts> findAllByScheduleId(Long scheduleId) {
        return jdbcRepo.findAllByScheduleId(scheduleId).stream().map(ContactsMapper::toModel).toList();
    }

    @Override
    public void deleteAllByScheduleId(Long scheduleId) {
        jpaRepo.deleteAllByScheduleId(scheduleId);
    }
}
