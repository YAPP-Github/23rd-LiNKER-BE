package com.imlinker.storage.contacts.mapper;

import com.imlinker.domain.common.model.Email;
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
                .school(entity.getSchool())
                .phoneNumber(PhoneNumber.of(entity.getPhoneNumber().getValue()))
                .email(Email.of(entity.getEmail().getValue()))
                .careers(entity.getCareers())
                .profileImgUrl(URL.of(entity.getProfileImgUrl()))
                .description(entity.getDescription())
                .build();
    }

    public static ContactsEntity toEntity(Contacts model) {
        return ContactsEntity.builder()
                .id(model.id())
                .name(model.name())
                .userId(model.userId())
                .careers(model.careers())
                .school(model.school())
                .email(new SecureString(model.email().getValue()))
                .description(model.description())
                .phoneNumber(new SecureString(model.phoneNumber().getValue()))
                .profileImgUrl(model.profileImgUrl().getValue())
                .build();
    }
}
