package com.imlinker.domain.user;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public record UpdateUserParam(
        Long id, String name, Email email, URL profileImgUrl, List<Tag> interests) {}
