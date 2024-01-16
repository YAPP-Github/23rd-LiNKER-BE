package com.imlinker.coreapi.core.my.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record UpdateMyInfoRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "직업", nullable = true) String job,
        @Schema(description = "소속", nullable = true) String association,
        @Schema(description = "관심사") List<String> tags) {}
