package com.imlinker.domain.user;

import com.imlinker.domain.contacts.ContactsReader;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.Schedules;
import com.imlinker.domain.tag.Tag;
import com.imlinker.domain.user.model.MyProfile;
import com.imlinker.domain.user.model.User;
import com.imlinker.domain.user.model.UserInterest;
import com.imlinker.enums.OperationResult;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReader userReader;
    private final UserUpdater userUpdater;
    private final ContactsReader contactsReader;
    private final UserInterestReader userInterestReader;
    private final UserInterestUpdater userInterestUpdater;
    private final UserScheduleReader userScheduleReader;

    public MyProfile getMyProfile(Long userId) {
        User user = userReader.findById(userId);
        List<Tag> userInterests = userInterestReader.findAllByUserId(user.getId());
        List<Contacts> contacts = contactsReader.findContactsByUserId(user.getId());
        List<Schedules> upcomingSchedules =
                userScheduleReader.findUpcomingSchedules(userId, LocalDateTime.now());

        return MyProfile.builder()
                .id(userId)
                .name(user.getName())
                .profileImgUrl(user.getProfileImgUrl())
                .email(user.getEmail())
                .interests(userInterests)
                .contactsNum(contacts.size())
                .scheduleNum(upcomingSchedules.size())
                .build();
    }

    @Transactional
    public OperationResult update(UpdateUserParam param) {
        User user = userUpdater.update(param.getId(), param.getName(), param.getEmail());

        List<UserInterest> userInterests =
                userInterestUpdater.update(param.getId(), param.getInterests());

        return OperationResult.SUCCESS;
    }
}
