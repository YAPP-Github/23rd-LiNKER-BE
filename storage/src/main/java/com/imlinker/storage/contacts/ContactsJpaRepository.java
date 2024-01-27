package com.imlinker.storage.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsJpaRepository extends JpaRepository<ContactsEntity,Long> {
}
