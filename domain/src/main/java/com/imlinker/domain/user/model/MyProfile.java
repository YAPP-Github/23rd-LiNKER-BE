package com.imlinker.domain.user.model;

import com.imlinker.domain.common.Email;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.tag.Tag;

import java.util.List;

public record MyProfile(
    Long id,
    String name,
    URL profileImgUrl,
    Email email,
    List<Tag> tags,
   int contactsNum,
   int scheduleNum) {}
