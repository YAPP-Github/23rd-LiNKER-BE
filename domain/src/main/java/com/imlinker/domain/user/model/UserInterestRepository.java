package com.imlinker.domain.user.model;

import java.util.List;

public interface UserInterestRepository {

    List<UserInterest> findAllByUserId(Long userId);

    UserInterest save(UserInterest userInterest);
}
