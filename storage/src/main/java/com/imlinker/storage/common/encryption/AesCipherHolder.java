package com.imlinker.storage.common.encryption;

import com.imlinker.storage.config.DatabaseEncryptionProperties;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AesCipherHolder {

    private final DatabaseEncryptionProperties properties;

    @Getter private static AesCipher instance;

    @PostConstruct
    public void initialize() {
        log.info("[AesCipherHolder][Initialize]");
        instance = new AesCipher(properties.getKey(), properties.getIv(), properties.getEnable());
    }
}
