package com.imlinker.storage.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_vendor")
    private String oauthVendor;

    @Column(name = "oauth_identifier")
    private String oauthIdentifier;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
