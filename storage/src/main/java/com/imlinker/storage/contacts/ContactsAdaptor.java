package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.storage.contacts.mapper.ContactsMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsAdaptor implements ContactsRepository {

    private final ContactsJpaRepository contactsJpaRepository;

    @Override
    public List<Contacts> findAllByUserId(Long userId) {
        return contactsJpaRepository.findAllByUserId(userId).stream()
                .map(ContactsMapper::toModel)
                .toList();
    }
}
