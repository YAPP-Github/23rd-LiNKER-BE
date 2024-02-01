package com.imlinker.domain.user;

import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.domain.user.model.UserInterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserInterestUpdater {
    private final UserInterestRepository userInterestRepository;

    public List<UserInterest> update(Long userId, List<Tag> interests) {
        userInterestRepository.deleteAllByUserId(userId);
        List<UserInterest> updatedInterests =
                interests.stream()
                        .map(interest -> new UserInterest(null, userId, interest.getId()))
                        .toList();

        return userInterestRepository.saveAll(updatedInterests);
    }
}
