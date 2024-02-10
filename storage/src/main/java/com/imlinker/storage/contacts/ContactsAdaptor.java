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

    private final ContactsJpaRepository jpaRepo;
    private final LocalCache<Long, List<Contacts>> userContactsCache =
            new LocalCache<>(100, Duration.ofSeconds(5));

    @Override
    public Optional<Contacts> findById(Long contactId) {
        return jpaRepo.findById(contactId).map(ContactsMapper::toModel);
    }

    @Override
    public Optional<Contacts> findByIdAndUserId(Long id, Long userId) {
        return jpaRepo.findByIdAndUserId(id, userId).map(ContactsMapper::toModel);
    }

    @Override
    public List<Contacts> findAllByIdIn(List<Long> contactIds) {
        return jpaRepo.findAllByIdIn(contactIds).stream().map(ContactsMapper::toModel).toList();
    }

    @Override
    public List<Contacts> findAllByUserId(Long userId) {
        return userContactsCache.getOrPut(
                userId, (k) -> jpaRepo.findAllByUserId(k).stream().map(ContactsMapper::toModel).toList());
    }

    @Override
    public List<Contacts> findAllContactsByUserIdAndContactsIdIn(Long userId, List<Long> contactIds) {
        return jpaRepo.findAllByUserIdAndIdIn(userId, contactIds).stream()
                .map(ContactsMapper::toModel)
                .toList();
    }

    @Override
    public Contacts save(Contacts contacts) {
        return ContactsMapper.toModel(jpaRepo.save(ContactsMapper.toEntity(contacts)));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
