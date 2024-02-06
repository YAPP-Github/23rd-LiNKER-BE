package com.imlinker.domain.contacts.model;

import java.util.List;
import java.util.Optional;

public interface ContactsRepository {
    Optional<Contacts> findById(Long contactId);

    Optional<Contacts> findByIdAndUserId(Long id, Long userId);

    List<Contacts> findAllByUserId(Long userId);

    Contacts save(Contacts contacts);
}
