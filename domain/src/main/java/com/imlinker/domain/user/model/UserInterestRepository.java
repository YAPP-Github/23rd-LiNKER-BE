package com.imlinker.domain.user.model;

import java.util.List;

public interface UserInterestRepository {

    List<UserInterest> findAllByUserId(Long userId);

    UserInterest save(UserInterest userInterest);

    List<UserInterest> saveAll(List<UserInterest> userInterests);

    void deleteAllByUserId(Long userId);
}
