package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.contacts.model.ContactsRepository;
import com.imlinker.domain.schedules.model.ScheduleParticipant;
import com.imlinker.domain.schedules.model.ScheduleParticipantRepository;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsReader {

    private final ContactsRepository contactsRepository;
    private final ScheduleParticipantRepository scheduleParticipantRepository;

    public List<Contacts> findScheduleParticipants(Long scheduleId) {
        List<Long> contactIds =
                scheduleParticipantRepository.findAllByScheduleId(scheduleId).stream()
                        .map(ScheduleParticipant::contactId)
                        .toList();

        return contactsRepository.findAllByIdIn(contactIds);
    }

    public Contacts findContactByIdAndUserId(Long contactId, Long userId) {
        return contactsRepository
                .findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new ApplicationException(ErrorType.CONTACT_NOT_FOUND));
    }

    public List<Contacts> findContactsByUserId(Long userId) {
        return contactsRepository.findAllByUserId(userId);
    }

    public LocalDate findContactRecentMeetingTime(Long contactId) {
        return scheduleParticipantRepository.findRecentMeetingTimeByContactId(contactId);
    }
}
