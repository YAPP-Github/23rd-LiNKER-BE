package com.imlinker.domain.contacts;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record UpdateContactParam(
        Long id,
        String name,
        Long userId,
        URL profileImgUrl,
        PhoneNumber phoneNumber,
        Email email,
        String school,
        String careers,
        String description,
        List<Tag> interests) {}
