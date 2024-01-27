package com.imlinker.domain.contacts;

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
    private URL profileImgUrl;
    private String description;
}
