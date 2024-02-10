package com.imlinker.storage.common.converters;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import com.imlinker.storage.common.model.SecureString;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.AttributeConverter;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JdbcConverterRegistry {
    private static final Map<Class<?>, AttributeConverter<?, ?>> registry = new LinkedHashMap<>();

    public static AttributeConverter<?, ?> getConverter(Class clazz) {
        AttributeConverter<?, ?> converter = registry.get(clazz);
        if (converter == null) {
            log.info("[JdbcConverterRegistry][Converter Not Found] (type={})", clazz.getName());
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR);
        }
        return converter;
    }

    @PostConstruct
    public void initialize() {
        log.info("[JdbcConverter][Initialize]");
        registry.put(SecureString.class, new SecureStringConverter());
    }
}
