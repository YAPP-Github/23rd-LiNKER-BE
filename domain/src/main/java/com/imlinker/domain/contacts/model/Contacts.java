package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import lombok.Builder;

@Builder
public record Contacts(
        Long id,
        Long userId,
        String name,
        String school,
        String careers,
        PhoneNumber phoneNumber,
        Email email,
        URL profileImgUrl,
        String description) {

    public Contacts update(
            String name,
            String school,
            String careers,
            PhoneNumber phoneNumber,
            Email email,
            URL profileImgUrl,
            String description) {
        return new Contacts(
                id, userId, name, school, careers, phoneNumber, email, profileImgUrl, description);
    }
}
