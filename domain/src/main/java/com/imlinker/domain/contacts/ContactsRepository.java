package com.imlinker.domain.contacts;

import java.util.List;

public interface ContactsRepository {
    List<Contacts> findAllByUserId(Long userId);
}
