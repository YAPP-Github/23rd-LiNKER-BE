package com.imlinker.domain.tag;

import com.imlinker.domain.tag.model.Platform;
import com.imlinker.domain.tag.model.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlatformReader {
    private final PlatformRepository platformRepository;

    public Platform findById(Long id) {
        return platformRepository.findById(id);
    }
}
