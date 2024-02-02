package com.imlinker.domain.contacts;

import com.imlinker.domain.common.URL;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsUpdater {

    private final ContactsRepository contactsRepository;

    public Contacts create(
            String name,
            Long userId,
            String profileImgUrl,
            String job,
            String association,
            String description) {

        return contactsRepository.save(
                new Contacts(null, userId, name, job, association, URL.of(profileImgUrl), description));
    }

    public Contacts fetch(Long id) {
        return contactsRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorType.CONTACT_NOT_FOUND));
    }
}
