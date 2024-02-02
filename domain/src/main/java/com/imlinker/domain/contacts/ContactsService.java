package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactProfile;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.tag.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsReader contactsReader;
    private final ContactInterestReader contactInterestReader;

    public ContactProfile getContactProfile(Long contactId, Long userId) {
        Contacts contact = contactsReader.findContactByIdAndUserId(userId, contactId);
        List<Tag> contactInterests = contactInterestReader.findAllByContact(contact);

        return new ContactProfile(contact, contactInterests);
    }

    public List<Contacts> getAllContacts(Long userId) {
        return contactsReader.findContactsByUserId(userId);
    }
}
