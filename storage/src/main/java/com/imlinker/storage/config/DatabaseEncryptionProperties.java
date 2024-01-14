package com.imlinker.storage.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "database.encryption")
public class DatabaseEncryptionProperties {
    private String key;
    private String iv;
    private Boolean enable;
}
