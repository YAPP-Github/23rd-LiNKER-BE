package com.imlinker.domain.user;

import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.tag.model.TagRepository;
import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.domain.user.model.UserInterestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInterestReader {

    private final TagRepository tagRepository;
    private final UserInterestRepository userInterestRepository;

    List<Tag> findAllByUserId(Long userId) {
        List<Long> userInterestTags =
                userInterestRepository.findAllByUserId(userId).stream().map(UserInterest::tagId).toList();
        return tagRepository.findAllByIdIn(userInterestTags);
    }
}
