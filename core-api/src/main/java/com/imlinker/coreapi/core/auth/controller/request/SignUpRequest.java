package com.imlinker.coreapi.core.auth.controller.request;

import com.imlinker.domain.tag.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record SignUpRequest(
        @Schema(description = "oAuth 식별자") String oAuthId,
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "핸드폰 번호") String phoneNumber,
        @Schema(description = "관심사") List<Tag> tags) {}
