package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import lombok.Builder;

@Builder
public record Contacts(
        Long id,
        Long userId,
        String name,
        String job,
        String association,
        PhoneNumber phoneNumber,
        URL profileImgUrl,
        String description) {

    public Contacts update(
            String name,
            String job,
            String association,
            PhoneNumber phoneNumber,
            URL profileImgUrl,
            String description) {
        return new Contacts(
                id, userId, name, job, association, phoneNumber, profileImgUrl, description);
    }
}
