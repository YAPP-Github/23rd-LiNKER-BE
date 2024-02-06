package com.imlinker.coreapi.core.contacts.request;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.contacts.UpdateContactParam;
import com.imlinker.domain.tag.model.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record UpdateContactRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "핸드폰번호", nullable = true) String phoneNumber,
        @Schema(description = "직업", nullable = true) String job,
        @Schema(description = "소속", nullable = true) String association,
        @Schema(description = "설명", nullable = true) String description,
        @Schema(description = "관심사") List<Tag> interests) {
    public UpdateContactParam toParam(Long contactId, Long userId) {
        return new UpdateContactParam(
                contactId,
                name,
                userId,
                URL.of(profileImgUrl),
                job,
                PhoneNumber.of(phoneNumber),
                association,
                description,
                interests);
    }
}
