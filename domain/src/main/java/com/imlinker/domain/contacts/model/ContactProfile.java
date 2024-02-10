package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.model.Tag;
import java.time.LocalDate;
import java.util.List;

public record ContactProfile(
        Long id,
        Long userId,
        String name,
        PhoneNumber phoneNumber,
        Email email,
        String school,
        String careers,
        URL profileImgUrl,
        String description,
        List<Tag> interests,
        LocalDate recentMeetingDate) {
    public ContactProfile(Contacts contacts, List<Tag> interests, LocalDate recentMeetingDate) {
        this(
                contacts.id(),
                contacts.userId(),
                contacts.name(),
                contacts.phoneNumber(),
                contacts.email(),
                contacts.school(),
                contacts.careers(),
                contacts.profileImgUrl(),
                contacts.description(),
                interests,
                recentMeetingDate);
    }
}
