package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record ContactProfile(
        Long id,
        Long userId,
        String name,
        PhoneNumber phoneNumber,
        String job,
        String association,
        URL profileImgUrl,
        String description,
        List<Tag> interests) {
    public ContactProfile(Contacts contacts, List<Tag> interests) {
        this(
                contacts.id(),
                contacts.userId(),
                contacts.name(),
                contacts.phoneNumber(),
                contacts.job(),
                contacts.association(),
                contacts.profileImgUrl(),
                contacts.description(),
                interests);
    }
}
