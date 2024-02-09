package com.imlinker.coreapi.core.contacts.request;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.common.model.PhoneNumber;
import com.imlinker.domain.contacts.CreateContactParam;
import com.imlinker.domain.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record CreateContactRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "핸드폰번호") String phoneNumber,
        @Schema(description = "이메일", nullable = true) String email,
        @Schema(description = "학력", nullable = true) String school,
        @Schema(description = "경력", nullable = true) String careers,
        @Schema(description = "설명", nullable = true) String description,
        @Schema(description = "관심사") List<Tag> interests) {
    public CreateContactParam toParam(Long userId) {
        return new CreateContactParam(
                name,
                userId,
                profileImgUrl,
                PhoneNumber.of(phoneNumber),
                Email.of(email),
                school,
                careers,
                description,
                interests);
    }
}
