package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.Email;
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
            PhoneNumber phoneNumber,
            Email email,
            String school,
            String careers,
            String description) {

        return contactsRepository.save(
                new Contacts(
                        null,
                        userId,
                        name,
                        school,
                        careers,
                        phoneNumber,
                        email,
                        URL.of(profileImgUrl),
                        description));
    }

    public Contacts update(
            Long id,
            String name,
            Long userId,
            URL profileImgUrl,
            PhoneNumber phoneNumber,
            Email email,
            String school,
            String careers,
            String description) {
        Contacts updatedContact =
                fetch(id, userId)
                        .update(name, school, careers, phoneNumber, email, profileImgUrl, description);
        return contactsRepository.save(updatedContact);
    }

    public void delete(Long id) {
        contactsRepository.deleteById(id);
    }

    public Contacts fetch(Long id, Long userId) {
        return contactsRepository
                .findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.CONTACT_NOT_FOUND));
    }
}
