package com.imlinker.storage.user;

import com.imlinker.domain.user.User;
import com.imlinker.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {


}
