package com.imlinker.storage.schedules.mapper;

import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.storage.schedules.ScheduleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleMapper {
    public static Schedules toModel(ScheduleEntity entity) {
        return Schedules.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .color(entity.getColor())
                .description(entity.getDescription())
                .startDateTime(entity.getStartDateTime())
                .endDateTime(entity.getEndDateTime())
                .build();
    }

    public static ScheduleEntity toEntity(Schedules model) {
        return ScheduleEntity.builder()
                .id(model.id())
                .title(model.title())
                .category(model.category())
                .color(model.color())
                .description(model.description())
                .startDateTime(model.startDateTime())
                .endDateTime(model.endDateTime())
                .build();
    }
}
