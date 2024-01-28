package com.imlinker.domain.common.file;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileType {
    JPEG("image/jpeg");

    private final String contentType;
}
