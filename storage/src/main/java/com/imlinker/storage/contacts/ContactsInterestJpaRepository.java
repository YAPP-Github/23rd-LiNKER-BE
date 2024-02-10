package com.imlinker.storage.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsInterestJpaRepository extends JpaRepository<ContactInterestEntity, Long> {
    void deleteAllByContactId(Long contactId);
}
