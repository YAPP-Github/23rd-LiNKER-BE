package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.enums.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsReader contactsReader;
    private final ContactsUpdater contactsUpdater;
    private final ContactInterestUpdater contactInterestUpdater;
    private final ContactsBookmarkUpdater contactsBookmarkUpdater;

    @Transactional
    public OperationResult createContact(CreateContactParam param) {
        Contacts contact =
                contactsUpdater.create(
                        param.name(),
                        param.userId(),
                        param.profileImgUrl(),
                        param.phoneNumber(),
                        param.email(),
                        param.school(),
                        param.careers(),
                        param.description());

        contactInterestUpdater.update(contact.id(), param.interests());

        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult bookmark(Long userId, Long contactId) {
        contactsBookmarkUpdater.create(userId, contactId);
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult updateContact(UpdateContactParam param) {
        Contacts contact =
                contactsUpdater.update(
                        param.id(),
                        param.name(),
                        param.userId(),
                        param.profileImgUrl(),
                        param.phoneNumber(),
                        param.email(),
                        param.school(),
                        param.careers(),
                        param.description());

        contactInterestUpdater.update(contact.id(), param.interests());

        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult deleteContact(Long contactId, Long userId) {
        Contacts contact = contactsReader.findContactByIdAndUserId(userId, contactId);
        contactsUpdater.delete(contact.id());
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult unBookmark(Long userId, Long contactId) {
        contactsBookmarkUpdater.delete(userId, contactId);
        return OperationResult.SUCCESS;
    }
}
