package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.storage.contacts.mapper.ContactsInterestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInterestAdaptor implements ContactInterestRepository {

    private final ContactsInterestJpaRepository repo;

    @Override
    public List<ContactInterest> findAllByContactId(Long contactId) {
        return repo.findAllByContactId(contactId).stream()
                .map(ContactsInterestMapper::toModel)
                .toList();
    }

    @Override
    public ContactInterest save(ContactInterest contactInterest) {
        ContactInterestEntity entity = repo.save(ContactsInterestMapper.toEntity(contactInterest));
        return ContactsInterestMapper.toModel(entity);
    }

    @Override
    public List<ContactInterest> saveAll(List<ContactInterest> contactInterests) {
        List<ContactInterestEntity> entities =
                contactInterests.stream().map(ContactsInterestMapper::toEntity).toList();
        return repo.saveAll(entities).stream().map(ContactsInterestMapper::toModel).toList();
    }

    @Override
    public void deleteAllByContactId(Long contactId) {
        repo.deleteAllByContactId(contactId);
    }
}
