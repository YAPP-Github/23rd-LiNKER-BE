package com.imlinker.storage.common.encryption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AesCipherTest {
    @RepeatedTest(10)
    @DisplayName("AesCipher는 Singleton 객체여야 한다")
    public void aesCipherShouldBeSingleton() throws InterruptedException {
        assertTimeout(
                Duration.ofSeconds(5),
                () -> {
                    while (AesCipherHolder.getInstance() == null) {
                        // Waiting for AesCipherHolder @PostConstruct
                        Thread.sleep(1000);
                    }

                    AesCipher actual = AesCipherHolder.getInstance();
                    assertThat(AesCipherHolder.getInstance()).isEqualTo(actual);
                });
    }
}
