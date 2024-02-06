package com.imlinker.storage.schedules;

import com.imlinker.storage.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String category;
    String color;
    String description;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
}
