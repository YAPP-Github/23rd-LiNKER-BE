package com.imlinker.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTagParam {
    private Long platformId;
    private String name;
}
