package com.imlinker.storage.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(name = "user_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInterestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "ref_tag_id")
    private Long tagId;
}
