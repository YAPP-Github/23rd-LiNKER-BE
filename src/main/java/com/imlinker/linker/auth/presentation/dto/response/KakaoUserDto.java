package com.imlinker.linker.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("kakao_account")
    private Account account;

    @Getter
    @NoArgsConstructor
    static class Account {
        private String email;
        private Profile profile;
    }

    @Getter
    @NoArgsConstructor
    static class Profile {
        private String nickname;
        private String profile_image_url;
    }

    public String getSocialId(){
        return id.toString();
    }

    public String getEmail(){
        return account.getEmail();
    }

    public String getNickname() {
        return account.getProfile().getNickname();
    }

    public String getProfileUrl(){
        return account.getProfile().getProfile_image_url();
    }
}
