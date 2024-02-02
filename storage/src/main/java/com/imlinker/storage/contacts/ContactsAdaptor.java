package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.storage.contacts.mapper.ContactsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsAdaptor implements ContactsRepository {

    private final ContactsJpaRepository contactsJpaRepository;

    @Override
    public Optional<Contacts> findById(Long contactId) {
        return contactsJpaRepository.findById(contactId).map(ContactsMapper::toModel);
    }

    @Override
    public Optional<Contacts> findByIdAndUserId(Long id, Long userId) {
        return contactsJpaRepository.findByIdAndUserId(id, userId).map(ContactsMapper::toModel);
    }

    @Override
    public List<Contacts> findAllByUserId(Long userId) {
        return contactsJpaRepository.findAllByUserId(userId).stream()
                .map(ContactsMapper::toModel)
                .toList();
    }

    @Override
    public Contacts save(Contacts contacts) {
        return ContactsMapper.toModel(contactsJpaRepository.save(ContactsMapper.toEntity(contacts)));
    }
}
