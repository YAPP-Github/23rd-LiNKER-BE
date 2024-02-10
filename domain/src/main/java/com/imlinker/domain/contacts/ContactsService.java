package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.ContactInterest;
import com.imlinker.domain.contacts.model.ContactProfile;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.schedules.ScheduleParticipantReader;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.enums.OperationResult;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactsService {
    private final ContactsReader contactsReader;
    private final ContactsUpdater contactsUpdater;
    private final ContactInterestReader contactInterestReader;
    private final ContactInterestUpdater contactInterestUpdater;
    private final ScheduleParticipantReader scheduleParticipantReader;

    public ContactProfile getContactProfile(Long contactId, Long userId) {

        Contacts contact = contactsReader.findContactByIdAndUserId(contactId, userId);
        List<Tag> contactInterests = contactInterestReader.findAllByContact(contact);
        LocalDate recentMeetingTime = scheduleParticipantReader.findContactRecentMeetingTime(contactId);

        return new ContactProfile(contact, contactInterests, recentMeetingTime);
    }

    public List<Contacts> getAllContacts(Long userId) {
        return contactsReader.findContactsByUserId(userId);
    }

    public List<Contacts> search(String query, Long userId) {
        List<Contacts> allUserContacts = contactsReader.findContactsByUserId(userId);

        return allUserContacts.stream().filter(contact -> contact.name().contains(query)).toList();
    }

    @Transactional
    public OperationResult createContact(CreateContactParam param) {
        Contacts contact =
                contactsUpdater.create(
                        param.name(),
                        param.userId(),
                        param.profileImgUrl(),
                        param.phoneNumber(),
                        param.email(),
                        param.school(),
                        param.careers(),
                        param.description());

        List<ContactInterest> contactInterests =
                contactInterestUpdater.update(contact.id(), param.interests());

        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult updateContact(UpdateContactParam param) {
        Contacts contact =
                contactsUpdater.update(
                        param.id(),
                        param.name(),
                        param.userId(),
                        param.profileImgUrl(),
                        param.phoneNumber(),
                        param.email(),
                        param.school(),
                        param.careers(),
                        param.description());

        List<ContactInterest> contactInterests =
                contactInterestUpdater.update(contact.id(), param.interests());

        return OperationResult.SUCCESS;
    }

    @Transactional
    public OperationResult deleteContact(Long contactId, Long userId) {
        Contacts contact = contactsReader.findContactByIdAndUserId(userId, contactId);
        contactsUpdater.delete(contact.id());
        return OperationResult.SUCCESS;
    }
}
