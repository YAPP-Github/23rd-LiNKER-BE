package com.imlinker.coreapi.core.contacts.request;

import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.contacts.CreateContactParam;
import com.imlinker.domain.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record CreateContactRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "핸드폰번호", nullable = true) String phoneNumber,
        @Schema(description = "직업", nullable = true) String job,
        @Schema(description = "소속", nullable = true) String association,
        @Schema(description = "설명", nullable = true) String description,
        @Schema(description = "관심사") List<Tag> interests) {
    public CreateContactParam toParam(Long userId) {
        return new CreateContactParam(
                name,
                userId,
                profileImgUrl,
                job,
                PhoneNumber.of(phoneNumber),
                association,
                description,
                interests);
    }
}
