package com.imlinker.storage.contacts.mapper;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.storage.contacts.ContactInterestEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactsInterestMapper {
    public static ContactInterest toModel(ContactInterestEntity entity) {
        return new ContactInterest(entity.getId(), entity.getContactId(), entity.getTagId());
    }

    public static ContactInterestEntity toEntity(ContactInterest model) {
        return new ContactInterestEntity(model.id(), model.contactId(), model.interestId());
    }
}
