package com.imlinker.storage.contacts;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactInterestJdbcQueryRepositoryTest {
    @Autowired private ContactInterestJdbcQueryRepository contactInterestJdbcQueryRepository;

    @Test
    @DisplayName("contact_interest 연관관계 테이블에서 연락처를 가져올 수 있다")
    public void getContactsFromContactInterest() {
        assertDoesNotThrow(
                () -> {
                    contactInterestJdbcQueryRepository.findAllByContactId(1L);
                });
    }
}
