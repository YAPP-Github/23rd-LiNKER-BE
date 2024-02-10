package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactInterestRepository;
import com.imlinker.domain.tag.Tag;
import com.imlinker.storage.contacts.mapper.ContactsInterestMapper;
import com.imlinker.storage.tag.mapper.TagMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactInterestAdaptor implements ContactInterestRepository {

    private final ContactsInterestJpaRepository jpaRepo;
    private final ContactInterestJdbcQueryRepository jdbcRepo;

    @Override
    public List<Tag> findAllByContactId(Long contactId) {
        return jdbcRepo.findAllByContactId(contactId).stream().map(TagMapper::toModel).toList();
    }

    @Override
    public ContactInterest save(ContactInterest contactInterest) {
        ContactInterestEntity entity =
                jpaRepo.save(ContactsInterestMapper.toEntity(contactInterest));
        return ContactsInterestMapper.toModel(entity);
    }

    @Override
    public List<ContactInterest> saveAll(List<ContactInterest> contactInterests) {
        List<ContactInterestEntity> entities =
                contactInterests.stream().map(ContactsInterestMapper::toEntity).toList();
        return jpaRepo.saveAll(entities).stream().map(ContactsInterestMapper::toModel).toList();
    }

    @Override
    public void deleteAllByContactId(Long contactId) {
        jpaRepo.deleteAllByContactId(contactId);
    }
}
