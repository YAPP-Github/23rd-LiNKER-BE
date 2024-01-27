package com.imlinker.domain.common;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.regex.Pattern;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {

    private static final String EMAIL_REGEX =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private String value;

    public static Email of(String value) {
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        return new Email(value);
    }
}
