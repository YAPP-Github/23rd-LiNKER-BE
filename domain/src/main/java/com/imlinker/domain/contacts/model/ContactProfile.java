package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.PhoneNumber;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.tag.Tag;
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
