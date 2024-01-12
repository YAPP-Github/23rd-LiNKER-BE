package com.imlinker.linker.auth.presentation.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AuthReqDto {
    private String accessToken;
}
