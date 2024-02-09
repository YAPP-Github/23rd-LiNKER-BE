package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.tag.Tag;
import java.util.List;

public record CreateContactParam(
        String name,
        Long userId,
        String profileImgUrl,
        PhoneNumber phoneNumber,
        Email email,
        String school,
        String careers,
        String description,
        List<Tag> interests) {}
