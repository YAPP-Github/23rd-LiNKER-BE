package com.imlinker.storage.contacts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_interest")
public class ContactInterestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "ref_contact_id")
    Long contactId;

    @Column(name = "ref_tag_id")
    Long tagId;
}
