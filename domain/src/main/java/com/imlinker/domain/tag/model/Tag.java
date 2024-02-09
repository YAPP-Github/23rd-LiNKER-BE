package com.imlinker.domain.tag.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tag {
    private Long id;
    private Long platformId;
    private String name;
}
