package com.imlinker.domain;

import com.imlinker.domain.common.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User{

    private Long id;

    private String name;

    private Email email;

    private int contactNum;

    private int scheduleNum;
}
