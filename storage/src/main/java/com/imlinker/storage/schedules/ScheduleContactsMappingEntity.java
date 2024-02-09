package com.imlinker.storage.schedules;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "schedule_contacts_mapping")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleContactsMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;
    private Long contactId;
    private LocalDateTime scheduleStartAt;
    private LocalDateTime scheduleEndAt;
}
