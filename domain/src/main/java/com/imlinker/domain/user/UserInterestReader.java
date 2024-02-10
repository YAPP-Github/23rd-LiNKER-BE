package com.imlinker.domain.user;

import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.user.model.UserInterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInterestReader {

    private final UserInterestRepository userInterestRepository;

    List<Tag> findAllByUserId(Long userId) {
        return userInterestRepository.findAllByUserId(userId);
    }
}
