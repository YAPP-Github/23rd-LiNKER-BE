package com.imlinker.coreapi.core.my.request;

import com.imlinker.domain.common.model.Email;
import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.user.UpdateUserParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record UpdateMyInfoRequest(
        @Schema(description = "이름") String name,
        @Schema(description = "이메일") String email,
        @Schema(description = "관심사") List<Tag> interests) {
    public UpdateUserParam toParam(Long userId) {
        return new UpdateUserParam(userId, name, Email.of(email), interests);
    }
}
