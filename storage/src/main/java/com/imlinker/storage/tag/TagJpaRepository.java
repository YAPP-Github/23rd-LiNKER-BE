package com.imlinker.storage.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagJpaRepository extends JpaRepository<TagEntity,Long> {

    List<TagEntity> findAllByIdIn(List<Long> ids);
}
