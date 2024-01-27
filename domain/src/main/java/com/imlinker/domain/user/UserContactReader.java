package com.imlinker.domain.user;

import com.imlinker.domain.contacts.Contacts;
import com.imlinker.domain.contacts.ContactsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserContactReader {
    private final ContactsRepository contactsRepository;

    public List<Contacts> findContactsByUserId(Long userId) {
        return contactsRepository.findAllByUserId(userId);
    }
}
