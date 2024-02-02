package com.imlinker.domain.user;

import com.imlinker.domain.common.Email;
import com.imlinker.domain.tag.Tag;
import java.util.List;

public record UpdateUserParam(Long id, String name, Email email, List<Tag> interests) {}
