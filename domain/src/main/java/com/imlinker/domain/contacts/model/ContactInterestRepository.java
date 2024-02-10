package com.imlinker.domain.contacts.model;

import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public interface ContactInterestRepository {
    List<Tag> findAllByContactId(Long contactId);

    ContactInterest save(ContactInterest contactInterest);

    List<ContactInterest> saveAll(List<ContactInterest> contactInterests);

    void deleteAllByContactId(Long contactId);
}
