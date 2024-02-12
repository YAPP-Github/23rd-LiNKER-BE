package com.imlinker.storage.contacts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsBookmarkJpaRepository extends JpaRepository<ContactsBookmarkEntity, Long> {
    void deleteByUserIdAndContactId(Long userId, Long contactId);
}
