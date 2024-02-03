package com.imlinker.storage.contacts;

import com.imlinker.storage.common.converters.SecureStringConverter;
import com.imlinker.storage.common.model.BaseTimeEntity;
import com.imlinker.storage.common.model.SecureString;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "contacts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactsEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "job")
    private String job;

    @Column(name = "phone_number")
    @Convert(converter = SecureStringConverter.class)
    private SecureString phoneNumber;

    @Column(name = "association")
    private String association;

    @Column(name = "profile_img_url")
    private String profileImgUrl;
    // memo: 400자
    @Column(name = "description")
    private String description;
}
