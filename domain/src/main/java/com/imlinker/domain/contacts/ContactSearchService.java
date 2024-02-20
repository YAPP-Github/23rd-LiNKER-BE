package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactProfile;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.schedules.ScheduleParticipantReader;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.domain.tag.model.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactSearchService {

    private final ContactsReader contactsReader;
    private final ContactInterestReader contactInterestReader;
    private final ContactsBookmarkReader contactsBookmarkReader;
    private final ScheduleParticipantReader scheduleParticipantReader;
    private final ContactsScheduleSearchManager contactsScheduleSearchManager;
    private final ContactsInterestedNewsSearchManager contactsInterestedNewsSearchManager;

    public List<Contacts> getAllContacts(Long userId) {
        return contactsReader.findContactsByUserId(userId);
    }

    public ContactProfile getContactProfile(Long contactId, Long userId) {

        Contacts contact = contactsReader.findContactByIdAndUserId(contactId, userId);
        List<Tag> contactInterests = contactInterestReader.findAllByContact(contact);
        LocalDate recentMeetingTime = scheduleParticipantReader.findContactRecentMeetingTime(contactId);

        return new ContactProfile(contact, contactInterests, recentMeetingTime);
    }

    public List<TagSpecificNews> searchContactInterestRelatedNews(
            Long userId, Long contactId, int size) {
        Contacts contacts = contactsReader.findContactByIdAndUserId(contactId, userId);
        return contactsInterestedNewsSearchManager.searchContactInterestRelatedNews(contacts, size);
    }

    public List<ScheduleDetail> searchContactNearTermSchedules(
            int size, Long userId, Long contactsId, boolean isUpcoming) {
        Contacts contacts = contactsReader.findContactByIdAndUserId(contactsId, userId);
        return contactsScheduleSearchManager.searchContactsNearTermSchedules(
                size, contacts, isUpcoming, LocalDateTime.now());
    }

    public List<Contacts> search(String query, Long userId) {
        List<Contacts> allUserContacts = contactsReader.findContactsByUserId(userId);

        return allUserContacts.stream().filter(contact -> contact.name().contains(query)).toList();
    }

    public List<Contacts> getAllBookMarkContacts(Long userId) {
        return contactsBookmarkReader.findAllByUserId(userId);
    }
}
