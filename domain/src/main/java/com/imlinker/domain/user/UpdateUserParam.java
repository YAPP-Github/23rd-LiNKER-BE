package com.imlinker.domain.user;

import com.imlinker.domain.common.Email;
import com.imlinker.domain.tag.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserParam {
    private Long id;
    private String name;
    private Email email;
    private List<Tag> interests;
}
