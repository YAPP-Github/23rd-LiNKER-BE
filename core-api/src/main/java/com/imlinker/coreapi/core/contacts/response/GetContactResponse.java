package com.imlinker.coreapi.core.contacts.response;

import com.imlinker.domain.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record GetContactResponse(
        @Schema(description = "식별자") Long id,
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL", nullable = true) String profileImgUrl,
        @Schema(description = "전화번호", nullable = true) String phoneNumber,
        @Schema(description = "직업", nullable = true) String job,
        @Schema(description = "소속", nullable = true) String association,
        @Schema(description = "관심사") List<Tag> tags) {}
