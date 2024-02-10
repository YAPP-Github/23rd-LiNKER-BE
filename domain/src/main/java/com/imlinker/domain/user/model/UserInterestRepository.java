package com.imlinker.domain.user.model;

import com.imlinker.domain.tag.model.Tag;
import java.util.List;

public interface UserInterestRepository {

    List<Tag> findAllByUserId(Long userId);

    UserInterest save(UserInterest userInterest);

    List<UserInterest> saveAll(List<UserInterest> userInterests);

    void deleteAllByUserId(Long userId);
}
