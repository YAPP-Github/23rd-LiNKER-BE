package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsReader contactsReader;

    public List<Contacts> getAllContacts(Long userId) {
        return contactsReader.findContactsByUserId(userId);
    }
}
