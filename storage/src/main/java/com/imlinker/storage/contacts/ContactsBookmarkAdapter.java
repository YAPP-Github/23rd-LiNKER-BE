package com.imlinker.storage.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsBookmark;
import com.imlinker.domain.contacts.model.ContactsBookmarkRepository;
import com.imlinker.storage.contacts.mapper.ContactsBookmarkMapper;
import com.imlinker.storage.contacts.mapper.ContactsMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsBookmarkAdapter implements ContactsBookmarkRepository {

    private final ContactsBookmarkJpaRepository jpaRepo;
    private final ContactsBookmarkJdbcQueryRepository jdbcRepo;

    @Override
    public List<Contacts> findAllByUserId(Long userId) {
        return jdbcRepo.findAllByUserId(userId).stream().map(ContactsMapper::toModel).toList();
    }

    @Override
    public ContactsBookmark save(ContactsBookmark contactsBookmark) {
        ContactsBookmarkEntity entity = jpaRepo.save(ContactsBookmarkMapper.toEntity(contactsBookmark));
        return ContactsBookmarkMapper.toModel(entity);
    }

    @Override
    public void delete(Long userId, Long contactId) {
        jpaRepo.deleteByUserIdAndContactId(userId, contactId);
    }
}
