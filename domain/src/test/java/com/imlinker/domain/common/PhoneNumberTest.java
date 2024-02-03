package com.imlinker.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PhoneNumberTest {
    @ParameterizedTest
    @ValueSource(strings = {"010-0000-0000", "02-000-0000", "02-0000-0000", "010-000-0000"})
    public void 전화번호_형식이라면_객체를_생성한다(String validPhoneNumber) {
        PhoneNumber phoneNumber = assertDoesNotThrow(() -> PhoneNumber.of(validPhoneNumber));
        assertThat(phoneNumber.getValue()).isEqualTo(validPhoneNumber);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {"0-00000-0", "010-aaa-aaaa", "010-1234-567890", "aaa-1234-1234", "aa-12-1234"})
    public void 전화번호_형식이_아니라면_Exception을_발생시킨다(String invalidPhoneNumber) {
        ApplicationException exception =
                assertThrows(ApplicationException.class, () -> PhoneNumber.of(invalidPhoneNumber));
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.INVALID_REQUEST_PARAMETER);
    }
}
