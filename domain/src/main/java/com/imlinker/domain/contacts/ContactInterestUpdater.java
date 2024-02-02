package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.domain.tag.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ContactInterestUpdater {
    private final ContactInterestRepository contactInterestRepository;

    public List<ContactInterest> update(Long contactId, List<Tag> interests) {
        contactInterestRepository.deleteAllByContactId(contactId);
        List<ContactInterest> updatedInterests =
                interests.stream()
                        .map(interest -> new ContactInterest(null, contactId, interest.getId()))
                        .toList();

        return contactInterestRepository.saveAll(updatedInterests);
    }
}
