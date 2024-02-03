package com.imlinker.domain.common.model;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.regex.Pattern;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URL {

    private static final String URL_REGEX =
            "^((http|https)://)?(www\\.)?([a-zA-Z0-9가-힣-.]+)\\.[a-z]{2,6}(:\\d{1,5})?(/[a-zA-Z0-9가-힣%._~/?#-]*)?";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private String value;

    public static URL of(String value) {

        if (value != null && !URL_PATTERN.matcher(value).matches()) {
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        return new URL(value);
    }
}
