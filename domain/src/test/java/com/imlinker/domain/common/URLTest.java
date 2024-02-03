package com.imlinker.domain.common;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.imlinker.domain.common.model.URL;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class URLTest {

    @ParameterizedTest
    @ValueSource(
            strings = {
                "http://example.com/한글",
                "http://example.com/path%20with%20spaces",
                "http://www.example.com",
                "https://example.com",
                "http://example.com:8080",
            })
    public void URL_형식이라면_객체를_생성한다(String validURL) {
        URL url = assertDoesNotThrow(() -> URL.of(validURL));
        assertThat(url.getValue()).isEqualTo(validURL);
    }

    @ParameterizedTest
    @ValueSource(strings = {"http://example", "http://.com", "://example.com", "ftp://example.com"})
    public void URL_형식이_아니라면_Exception을_발생시킨다(String invalidURL) {
        ApplicationException exception =
                assertThrows(ApplicationException.class, () -> URL.of(invalidURL));
        assertThat(exception.getErrorType()).isEqualTo(ErrorType.INVALID_REQUEST_PARAMETER);
    }
}
