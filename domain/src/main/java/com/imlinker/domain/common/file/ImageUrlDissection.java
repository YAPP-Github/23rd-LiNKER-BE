package com.imlinker.domain.common.file;

import com.imlinker.domain.common.model.URL;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public record ImageUrlDissection(String uuid, Long userId, LocalDateTime uploadTime) {

    public static ImageUrlDissection generate(Long userId) {
        return new ImageUrlDissection(
                UUID.randomUUID().toString().replace("-", ""), userId, LocalDateTime.now());
    }

    public static ImageUrlDissection fromUrl(URL url) {
        String[] tokens = url.getValue().split("/");

        Long userId = Long.parseLong(tokens[3]);
        String uuid = tokens[4];
        LocalDateTime uploadTime =
                LocalDateTime.ofEpochSecond(Long.parseLong(tokens[5]), 0, ZoneOffset.of("+09:00"));

        return validate(new ImageUrlDissection(uuid, userId, uploadTime));
    }

    private static ImageUrlDissection validate(ImageUrlDissection dissection) {
        if (dissection.uuid == null
                || dissection.userId == null
                || dissection.uploadTime == null
                || dissection.userId <= 0
                || dissection.uploadTime.isAfter(LocalDateTime.now())) {
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        return dissection;
    }

    public String toUrlFormat() {
        return String.format(
                "%d/%s/%s", userId, uuid, uploadTime.toEpochSecond(ZoneOffset.of("+09:00")));
    }
}
