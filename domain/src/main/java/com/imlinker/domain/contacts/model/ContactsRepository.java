package com.imlinker.domain.contacts.model;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository {
    Optional<Contacts> findById(Long contactId);

    Optional<Contacts> findByIdAndUserId(Long id, Long userId);

    List<Contacts> findAllByIdIn(List<Long> contactIds);

    List<Contacts> findAllByUserId(Long userId);

    List<Contacts> findAllContactsByUserIdAndContactsIdIn(Long userId, List<Long> contactIds);

    Contacts save(Contacts contacts);

    void deleteById(Long id);
}
