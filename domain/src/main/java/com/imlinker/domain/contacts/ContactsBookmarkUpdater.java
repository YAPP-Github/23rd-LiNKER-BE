package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactsBookmark;
import com.imlinker.domain.contacts.model.ContactsBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsBookmarkUpdater {
    private final ContactsBookmarkRepository contactsBookmarkRepository;

    public ContactsBookmark create(Long userId, Long contactId) {
        return contactsBookmarkRepository.save(new ContactsBookmark(null, contactId, userId));
    }

    public void delete(Long userId, Long contactId) {
        contactsBookmarkRepository.delete(userId, contactId);
    }
}
