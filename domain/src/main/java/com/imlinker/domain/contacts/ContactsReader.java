package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsReader {

    private final ContactsRepository contactsRepository;

    public Contacts findContactByIdAndUserId(Long contactId, Long userId) {
        return contactsRepository
                .findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.CONTACT_NOT_FOUND));
    }

    public List<Contacts> findContactsByUserId(Long userId) {
        return contactsRepository.findAllByUserId(userId);
    }

    public List<Contacts> findAllContactsByUserIdAndContactsIdIn(Long userId, List<Long> contactsId) {
        return contactsRepository.findAllContactsByUserIdAndContactsIdIn(userId, contactsId);
    }
}
