package com.imlinker.linker.user.domain;

import com.imlinker.linker.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String socialId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String profileUrl;
    private String email;
    private String job;
    private String association;

    @Builder
    public User(String socialId, String name, String profileUrl, String email, String job, String association){
        this.socialId = socialId;
        this.name = name;
        this.profileUrl = profileUrl;
        this.email = email;
        this.job = job;
        this.association = association;
    }
}
