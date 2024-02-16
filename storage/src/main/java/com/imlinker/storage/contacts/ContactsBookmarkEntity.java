package com.imlinker.storage.contacts;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@Table(
        name = "contacts_bookmark",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"ref_user_id", "ref_contact_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContactsBookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ref_user_id")
    Long userId;

    @Column(name = "ref_contact_id")
    Long contactId;
}
