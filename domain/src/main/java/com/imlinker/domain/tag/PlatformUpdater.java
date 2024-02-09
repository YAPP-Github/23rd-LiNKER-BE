package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Platform;
import com.imlinker.domain.tag.model.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformUpdater {
    private final PlatformRepository platformRepository;

    public Platform create(String name, String section) {
        return platformRepository.save(new Platform(null, name, section));
    }
}
