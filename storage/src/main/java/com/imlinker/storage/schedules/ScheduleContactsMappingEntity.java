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

    @Column(name = "ref_schedule_id")
    private Long scheduleId;

    @Column(name = "ref_contact_id")
    private Long contactId;

    private LocalDateTime scheduleStartAt;
    private LocalDateTime scheduleEndAt;
}
