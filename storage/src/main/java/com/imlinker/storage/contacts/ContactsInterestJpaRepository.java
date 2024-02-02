package com.imlinker.storage.contacts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsInterestJpaRepository extends JpaRepository<ContactInterestEntity, Long> {
    List<ContactInterestEntity> findAllByContactId(Long contactId);
}
