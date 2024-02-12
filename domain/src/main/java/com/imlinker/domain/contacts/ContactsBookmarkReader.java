package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsBookmarkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsBookmarkReader {

    private final ContactsBookmarkRepository contactsBookmarkRepository;

    public List<Contacts> findAllByUserId(Long userId) {
        return contactsBookmarkRepository.findAllByUserId(userId);
    }
}
