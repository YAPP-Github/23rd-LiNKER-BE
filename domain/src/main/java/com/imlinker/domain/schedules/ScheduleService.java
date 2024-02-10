package com.imlinker.domain.schedules;

import com.imlinker.domain.contacts.ContactsReader;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.enums.OperationResult;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ContactsReader contactsReader;
    private final ScheduleUpdater scheduleUpdater;
    private final ScheduleParticipantUpdater scheduleParticipantUpdater;

    @Transactional
    public OperationResult create(CreateScheduleParam param) {
        List<Long> contactIds =
                contactsReader
                        .findAllContactsByUserIdAndContactsIdIn(param.userId(), param.contactIds())
                        .stream()
                        .map(Contacts::id)
                        .toList();
        if (contactIds.isEmpty()) {
            throw new ApplicationException(
                    ErrorType.INVALID_REQUEST_PARAMETER, "일정 참여자가 충분하지 않습니다.", null);
        }

        Schedules schedule =
                scheduleUpdater.create(
                        param.userId(),
                        param.title(),
                        param.category(),
                        param.color(),
                        param.description(),
                        contactIds.size(),
                        param.startDateTime(),
                        param.endDateTime());

        scheduleParticipantUpdater.create(schedule, contactIds);
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult update(UpdateScheduleParam param) {
        List<Long> contactIds =
                contactsReader
                        .findAllContactsByUserIdAndContactsIdIn(param.userId(), param.contactIds())
                        .stream()
                        .map(Contacts::id)
                        .toList();
        if (contactIds.isEmpty()) {
            throw new ApplicationException(
                    ErrorType.INVALID_REQUEST_PARAMETER, "일정 참여자가 충분하지 않습니다.", null);
        }
        Schedules schedule =
                scheduleUpdater.update(
                        param.scheduleId(),
                        param.userId(),
                        param.title(),
                        param.category(),
                        param.color(),
                        param.description(),
                        contactIds.size(),
                        param.startDateTime(),
                        param.endDateTime());
        scheduleParticipantUpdater.update(schedule, contactIds);
        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult delete(Long userId, Long scheduleId) {
        scheduleUpdater.delete(userId, scheduleId);
        scheduleParticipantUpdater.delete(scheduleId);
        return OperationResult.SUCCESS;
    }
}
