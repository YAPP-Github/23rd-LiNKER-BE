package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.localcache.LocalCache;
import com.imlinker.storage.contacts.mapper.ContactsMapper;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsAdaptor implements ContactsRepository {

    private final ContactsJpaRepository contactsJpaRepository;
    private final LocalCache<Long, List<Contacts>> userContactsCache =
            new LocalCache<>(100, Duration.ofSeconds(5));

    @Override
    public Optional<Contacts> findById(Long contactId) {
        return contactsJpaRepository.findById(contactId).map(ContactsMapper::toModel);
    }

    @Override
    public Optional<Contacts> findByIdAndUserId(Long id, Long userId) {
        return contactsJpaRepository.findByIdAndUserId(id, userId).map(ContactsMapper::toModel);
    }

    @Override
    public List<Contacts> findAllByIdIn(List<Long> contactIds) {
        return contactsJpaRepository.findAllByIdIn(contactIds).stream()
                .map(ContactsMapper::toModel)
                .toList();
    }

    @Override
    public List<Contacts> findAllByUserId(Long userId) {
        return userContactsCache.getOrPut(
                userId,
                (k) ->
                        contactsJpaRepository.findAllByUserId(k).stream()
                                .map(ContactsMapper::toModel)
                                .toList());
    }

    @Override
    public Contacts save(Contacts contacts) {
        return ContactsMapper.toModel(contactsJpaRepository.save(ContactsMapper.toEntity(contacts)));
    }

    @Override
    public void deleteById(Long id) {
        contactsJpaRepository.deleteById(id);
    }
}
