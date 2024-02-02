package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ContactInterestAdaptor implements ContactInterestRepository {
    @Override
    public List<ContactInterest> findAllByContactId(Long contactId) {
        return null;
    }

    @Override
    public ContactInterest save(ContactInterest contactInterest) {
        return null;
    }

    @Override
    public List<ContactInterest> saveAll(List<ContactInterest> contactInterests) {
        return null;
    }

    @Override
    public void deleteAllByContactId(Long contactId) {}
}
