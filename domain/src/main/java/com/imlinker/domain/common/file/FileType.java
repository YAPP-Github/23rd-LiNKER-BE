package com.imlinker.domain.common.file;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileType {
    IMAGE(List.of(new String[] {"image/jpeg", "image/png"}));

    private final List<String> contentType;

    public boolean isSupported(String type) {
        return contentType.contains(type);
    }
}
