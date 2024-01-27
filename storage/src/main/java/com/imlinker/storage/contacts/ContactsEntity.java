package com.imlinker.storage.contacts;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_img_url")
    private String profileImgUrl;
    // memo: 400자
    @Column(name = "description")
    private String description;
}
