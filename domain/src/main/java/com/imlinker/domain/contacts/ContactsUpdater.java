package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
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
            PhoneNumber phoneNumber,
            String association,
            String description) {

        return contactsRepository.save(
                new Contacts(
                        null, userId, name, job, association, phoneNumber, URL.of(profileImgUrl), description));
    }

    public Contacts update(
            Long id,
            String name,
            Long userId,
            URL profileImgUrl,
            String job,
            PhoneNumber phoneNumber,
            String association,
            String description) {
        Contacts updatedContact =
                fetch(id, userId).update(name, job, association, phoneNumber, profileImgUrl, description);
        return contactsRepository.save(updatedContact);
    }

    public Contacts fetch(Long id, Long userId) {
        return contactsRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.CONTACT_NOT_FOUND));
    }
}
