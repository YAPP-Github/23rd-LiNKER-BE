package com.imlinker.domain.common;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.regex.Pattern;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhoneNumber {
    private static final String PHONE_NUMBER_REGEX = "^(\\d{2,3})-(\\d{3,4})-(\\d{4})$";

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

    private String value;

    public static PhoneNumber of(String value) {
        if (!PHONE_NUMBER_PATTERN.matcher(value).matches()) {
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        return new PhoneNumber(value);
    }
}
