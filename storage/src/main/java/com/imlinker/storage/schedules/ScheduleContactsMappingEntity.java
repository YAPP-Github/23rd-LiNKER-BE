package com.imlinker.storage.schedules;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "schedule_contacts_mapping")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ScheduleContactsMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long scheduleId;

    Long contactId;
}
