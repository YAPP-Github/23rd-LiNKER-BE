package com.imlinker.domain.contacts.model;

import java.util.List;

public interface ContactsBookmarkRepository {
    List<Contacts> findAllByUserId(Long userId);

    ContactsBookmark save(ContactsBookmark contactsBookmark);

    void delete(Long userId, Long contactId);
}
