package com.imlinker.storage.contacts;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsJpaRepository extends JpaRepository<ContactsEntity, Long> {

    Optional<ContactsEntity> findByIdAndUserId(Long id, Long userId);

    List<ContactsEntity> findAllByUserId(Long userId);

    List<ContactsEntity> findAllByIdIn(List<Long> ids);
}
