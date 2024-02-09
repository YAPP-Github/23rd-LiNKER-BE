package com.imlinker.coreapi.core.contacts.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchContactResponse {

    @Schema(description = "연락처 List")
    public record Contacts(List<SimpleContact> contacts) {}
    ;

    @Schema(description = "연락처")
    public record SimpleContact(
            @Schema(description = "식별자") Long id,
            @Schema(description = "이름") String name,
            @Schema(description = "프로필 이미지 URL", nullable = true) String profileImgUrl,
            @Schema(description = "이메일", nullable = true) String email,
            @Schema(description = "학력", nullable = true) String school,
            @Schema(description = "경력", nullable = true) String careers) {}
}
