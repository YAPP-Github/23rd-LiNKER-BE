package com.imlinker.domain.contacts.model;

import com.imlinker.domain.common.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Contacts {
    private Long id;
    private Long userId;
    private String name;
    private String job;
    private String association;
    private URL profileImgUrl;
    private String description;
}
