package com.imlinker.storage.common.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.imlinker.storage.common.converters.JdbcConverterRegistry;
import com.imlinker.storage.common.converters.SecureStringConverter;
import com.imlinker.storage.common.model.SecureString;
import jakarta.persistence.AttributeConverter;
import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcConverterRegistryTest {
    @RepeatedTest(10)
    @DisplayName("JdbcConverterRegistry에서 관리되는 Converter는 Singleton 객체여야 한다")
    public void converterInRegistryShouldBeSingleton() {
        assertTimeout(
                Duration.ofSeconds(5),
                () -> {
                    while (JdbcConverterRegistry.getConverter(SecureString.class) == null) {
                        // Waiting for AesCipherHolder @PostConstruct
                        Thread.sleep(1000);
                    }

                    AttributeConverter expected = JdbcConverterRegistry.getConverter(SecureString.class);
                    assertInstanceOf(SecureStringConverter.class, expected);
                    assertThat((SecureStringConverter) JdbcConverterRegistry.getConverter(SecureString.class))
                            .isEqualTo(expected);
                });
    }
}
