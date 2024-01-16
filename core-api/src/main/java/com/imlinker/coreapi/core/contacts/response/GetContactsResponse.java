package com.imlinker.coreapi.core.contacts.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetContactsResponse {
    @Schema(description = "연락처 List")
    public record Contacts(List<SimpleContact> contacts) {}

    @Schema(description = "연락처")
    public record SimpleContact(
            @Schema(description = "식별자") Long id,
            @Schema(description = "이름") String name,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl,
            @Schema(description = "직업", nullable = true) String job,
            @Schema(description = "소속", nullable = true) String association) {}
}
