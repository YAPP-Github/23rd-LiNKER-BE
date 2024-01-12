package com.imlinker.linker.config;


import com.imlinker.linker.common.exception.custom.BusinessException;
import com.imlinker.linker.common.exception.enums.ErrorMessage;
import com.imlinker.linker.user.domain.User;
import com.imlinker.linker.user.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String socialId) {
        User user = userRepository.findBySocialId(socialId)
                .orElseThrow(() -> {
                    return new BusinessException(ErrorMessage.SOCIAL_ID_INVALID);
                });

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getSocialId(), user.getEmail(), grantedAuthorities);
    }

}