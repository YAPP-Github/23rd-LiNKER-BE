package com.imlinker.localcache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.function.Function;

public class LocalCache<K, V> {
    private final Cache<K, V> cache;

    public LocalCache(int size, Duration lifeSpan) {
        this.cache = Caffeine.newBuilder().maximumSize(size).expireAfterWrite(lifeSpan).build();
    }

    public V get(K key) {
        return cache.getIfPresent(key);
    }

    public V getOrPut(K key, Function<K, V> function) {
        return cache.get(key, function);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public void delete(K key) {
        cache.invalidate(key);
    }
}
