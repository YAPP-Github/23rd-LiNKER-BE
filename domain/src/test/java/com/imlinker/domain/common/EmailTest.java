package com.imlinker.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.imlinker.domain.common.model.Email;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"rlaxowns7916@gmail.com", "rlaxowns7916.utube@gmail.com"})
    public void 이메일_형식이라면_객체를_생성한다(String validEmail) {
        Email email = assertDoesNotThrow(() -> Email.of(validEmail));
        assertThat(email.getValue()).isEqualTo(validEmail);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "example@t.co",
                "example@test..com",
                "example@te st.com",
                "example@test.com\\n",
                "example@test_test.com:"
            })
    public void 이메일_형식이_아니라면_Exception을_발생시킨다(String invalidEmail) {
        ApplicationException exception =
                assertThrows(ApplicationException.class, () -> Email.of(invalidEmail));
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.INVALID_REQUEST_PARAMETER);
    }
}
