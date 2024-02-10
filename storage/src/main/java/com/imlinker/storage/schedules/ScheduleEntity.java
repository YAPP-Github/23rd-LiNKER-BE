package com.imlinker.storage.schedules;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ref_user_id")
    Long userId;

    String title;
    String category;
    String color;
    String description;
    int participantsNum;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static RowMapper<ScheduleEntity> getRowMapper() {
        return ((rs, rowNum) ->
                new ScheduleEntity(
                        rs.getLong("id"),
                        rs.getLong("ref_user_id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getString("color"),
                        rs.getString("description"),
                        rs.getInt("participants_num"),
                        rs.getObject("start_date_time", LocalDateTime.class),
                        rs.getObject("end_date_time", LocalDateTime.class),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class)));
    }
}
