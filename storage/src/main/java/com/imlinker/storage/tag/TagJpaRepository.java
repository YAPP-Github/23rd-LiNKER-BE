package com.imlinker.storage.tag;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findAllByIdIn(List<Long> ids);
}
