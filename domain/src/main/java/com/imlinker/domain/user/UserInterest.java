package com.imlinker.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInterest {
    private Long id;

    private Long userId;

    private Long tagId;
}
