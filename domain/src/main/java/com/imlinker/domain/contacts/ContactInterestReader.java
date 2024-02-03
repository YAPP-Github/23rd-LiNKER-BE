package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.tag.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactInterestReader {

    private final TagRepository tagRepository;
    private final ContactsRepository contactsRepository;
    private final ContactInterestRepository contactInterestRepository;

    List<Tag> findAllByContact(Contacts contact) {
        List<Long> contactInterestTags =
                contactInterestRepository.findAllByContactId(contact.id()).stream()
                        .map(ContactInterest::interestId)
                        .toList();
        return tagRepository.findAllByIdIn(contactInterestTags);
    }
}
