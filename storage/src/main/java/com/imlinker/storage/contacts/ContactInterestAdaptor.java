package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.storage.contacts.mapper.ContactsInterestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInterestAdaptor implements ContactInterestRepository {

    private final ContactsInterestJpaRepository contactsInterestJpaRepository;

    @Override
    public List<ContactInterest> findAllByContactId(Long contactId) {
        return contactsInterestJpaRepository.findAllByContactId(contactId).stream()
                .map(ContactsInterestMapper::toModel)
                .toList();
    }

    @Override
    public ContactInterest save(ContactInterest contactInterest) {
        // TODO()
        return null;
    }

    @Override
    public List<ContactInterest> saveAll(List<ContactInterest> contactInterests) {
        // TODO()
        return null;
    }

    @Override
    public void deleteAllByContactId(Long contactId) {
        // TODO()
    }
}
