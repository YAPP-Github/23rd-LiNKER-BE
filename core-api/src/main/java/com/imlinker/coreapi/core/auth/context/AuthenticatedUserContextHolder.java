package com.imlinker.coreapi.core.auth.context;

import com.imlinker.domain.common.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AuthenticatedUserContextHolder {
    private Long id;
    private Email email;
}
