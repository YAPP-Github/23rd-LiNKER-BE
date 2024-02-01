package com.imlinker.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTagParam {
    private String section;
    private String name;
}
