package com.imlinker.coreapi.core.my.response;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.user.model.MyProfile;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record GetMyResponse(
        @Schema(description = "이름") String name,
        @Schema(description = "프로필 이미지 URL") String profileImgUrl,
        @Schema(description = "이메일") String email,
        @Schema(description = "관심사") List<Tag> tags,
        @Schema(description = "연락처 수") int contactsNum,
        @Schema(description = "일정 수") int scheduleNum) {

    public static GetMyResponse of(MyProfile profile) {
        return new GetMyResponse(
                profile.name(),
                profile.profileImgUrl().getValue(),
                profile.email().getValue(),
                profile.interests(),
                profile.contactsNum(),
                profile.scheduleNum());
    }
}
