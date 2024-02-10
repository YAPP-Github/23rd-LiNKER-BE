package com.imlinker.domain.common.model;

import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.regex.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URL {

    private static final String URL_REGEX =
            "^((http|https)://)?([a-zA-Z0-9가-힣.]+)\\.[a-z]{2,20}(:\\d{1,20})?(/[a-zA-Z0-9가-힣%._~/?#-]*)?$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    private String value;

    public static URL empty() {
        return new URL(null);
    }

    public static URL of(String value) {
        if (value != null && !URL_PATTERN.matcher(value).matches()) {
            log.info("[URL][Invalid] (value: {})", value);
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        return new URL(value);
    }
}
