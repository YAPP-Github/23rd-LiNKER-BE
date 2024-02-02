package com.imlinker.storage.contacts.mapper;

import com.imlinker.domain.common.URL;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.storage.contacts.ContactsEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactsMapper {
    public static Contacts toModel(ContactsEntity entity) {
        return Contacts.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .job(entity.getJob())
                .association(entity.getAssociation())
                .profileImgUrl(URL.of(entity.getProfileImgUrl()))
                .description(entity.getDescription())
                .build();
    }

    public static ContactsEntity toEntity(Contacts model) {
        return ContactsEntity.builder()
                .id(model.getId())
                .userId(model.getUserId())
                .job(model.getJob())
                .association(model.getAssociation())
                .description(model.getDescription())
                .profileImgUrl(model.getProfileImgUrl().getValue())
                .build();
    }
}
