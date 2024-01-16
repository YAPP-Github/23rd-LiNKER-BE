package com.imlinker.coreapi.core.contacts.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateContactRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "직업", nullable = true) String job,
        @Schema(description = "소속", nullable = true) String association) {}
