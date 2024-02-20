package com.imlinker.storage.contacts;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_interest")
@EntityListeners(AuditingEntityListener.class)
public class ContactInterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ref_contact_id")
    Long contactId;

    @Column(name = "ref_tag_id")
    Long tagId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
