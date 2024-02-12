package com.imlinker.storage.contacts.mapper;

import com.imlinker.domain.contacts.model.ContactsBookmark;
import com.imlinker.storage.contacts.ContactsBookmarkEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactsBookmarkMapper {
    public static ContactsBookmark toModel(ContactsBookmarkEntity entity) {
        return new ContactsBookmark(entity.getId(), entity.getContactId(), entity.getUserId());
    }

    public static ContactsBookmarkEntity toEntity(ContactsBookmark model) {
        return new ContactsBookmarkEntity(model.id(), model.userId(), model.contactId());
    }
}
