package com.imlinker.coreapi.core.auth.security.oauth2;

import com.imlinker.localcache.LocalCache;
import java.time.Duration;
import org.springframework.stereotype.Component;

@Component
public class CustomClientOriginHostCache {
    private final LocalCache<String, String> cache = new LocalCache<>(100, Duration.ofMinutes(3));

    public String getClientOriginHost(String key) {
        if (key == null) {
            return null;
        }
        return cache.get(key);
    }

    public void putClientOriginHost(String key, String value) {
        cache.put(key, value);
    }

    public void deleteClientOriginHost(String key) {
        cache.delete(key);
    }
}
