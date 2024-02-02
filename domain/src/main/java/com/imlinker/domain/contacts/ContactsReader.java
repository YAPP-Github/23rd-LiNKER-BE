package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsReader {

    private final ContactsRepository contactsRepository;

    public List<Contacts> findContactsByUserId(Long userId) {
        return contactsRepository.findAllByUserId(userId);
    }
}
