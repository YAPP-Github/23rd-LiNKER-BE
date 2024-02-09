package com.imlinker.storage.schedules.mapper;

import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.storage.schedules.ScheduleContactsMappingEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleContactMappingMapper {
    public static ScheduleParticipant toModel(ScheduleContactsMappingEntity entity) {
        return new ScheduleParticipant(
                entity.getScheduleId(),
                entity.getContactId(),
                entity.getScheduleStartAt(),
                entity.getScheduleEndAt());
    }

    public static ScheduleContactsMappingEntity toEntity(ScheduleParticipant model) {
        return new ScheduleContactsMappingEntity(
                null,
                model.scheduleId(),
                model.contactId(),
                model.scheduleStartAt(),
                model.scheduleEndAt());
    }
}
