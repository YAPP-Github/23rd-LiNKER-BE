package com.imlinker.domain.contacts.model;

import java.util.List;

public interface ContactInterestRepository {
    List<ContactInterest> findAllByContactId(Long contactId);

    ContactInterest save(ContactInterest contactInterest);

    List<ContactInterest> saveAll(List<ContactInterest> contactInterests);

    void deleteAllByContactId(Long contactId);
}
