package com.imlinker.domain.user;

import com.imlinker.domain.common.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User{

    private Long id;

    private String name;

    private Email email;

    private String association;

    private String job;

    private int contactNum;

    private int scheduleNum;
}