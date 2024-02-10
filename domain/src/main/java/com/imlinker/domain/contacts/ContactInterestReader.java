package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.tag.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactInterestReader {
    private final ContactInterestRepository contactInterestRepository;

    List<Tag> findAllByContact(Contacts contact) {
        return contactInterestRepository.findAllByContactId(contact.id());
    }
}
