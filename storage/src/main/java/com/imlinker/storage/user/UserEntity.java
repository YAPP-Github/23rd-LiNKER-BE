package com.imlinker.storage.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String association;

    @Column
    private String job;

    @Column
    private int contactNum;

    @Column
    private int scheduleNum;

    @Column
    private LocalDateTime createAt;

    @Column
    private LocalDateTime updatedAt;
}
