package com.imlinker.domain.contacts.model;

import java.util.List;

public interface ContactsRepository {
    List<Contacts> findAllByUserId(Long userId);
}
