package com.imlinker.domain.user;

import com.imlinker.domain.common.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean isMember(Email email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
