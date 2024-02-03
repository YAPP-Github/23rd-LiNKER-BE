package com.imlinker.storage.contacts.mapper;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.storage.common.model.SecureString;
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
                .phoneNumber(PhoneNumber.of(entity.getPhoneNumber().getValue()))
                .association(entity.getAssociation())
                .profileImgUrl(URL.of(entity.getProfileImgUrl()))
                .description(entity.getDescription())
                .build();
    }

    public static ContactsEntity toEntity(Contacts model) {
        return ContactsEntity.builder()
                .id(model.id())
                .userId(model.userId())
                .job(model.job())
                .association(model.association())
                .description(model.description())
                .phoneNumber(new SecureString(model.phoneNumber().getValue()))
                .profileImgUrl(model.profileImgUrl().getValue())
                .build();
    }
}
