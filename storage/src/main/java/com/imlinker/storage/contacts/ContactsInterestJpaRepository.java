package com.imlinker.storage.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsInterestJpaRepository extends JpaRepository<ContactInterestEntity, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from ContactInterestEntity ci where ci.contactId = :contactId")
    void deleteAllByContactId(Long contactId);
}
