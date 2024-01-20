package com.imlinker.domain.user;

import java.util.List;

public interface UserInterestRepository {

    List<UserInterest> findAllByUserId(Long userId);

    UserInterest save(UserInterest userInterest);
}
