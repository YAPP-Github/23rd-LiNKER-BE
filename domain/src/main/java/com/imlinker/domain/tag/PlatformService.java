package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PlatformReader platformReader;

    public Platform getPlatform(Long id) {
        return platformReader.findById(id);
    }
}
