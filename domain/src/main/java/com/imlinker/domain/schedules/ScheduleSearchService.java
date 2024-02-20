package com.imlinker.domain.schedules;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.contacts.ContactInterestReader;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.news.NewsReader;
import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.domain.schedules.model.Schedules;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.domain.user.UserReader;
import com.imlinker.domain.user.model.User;
import com.imlinker.pagination.CursorPaginationResult;
import java.time.LocalDateTime;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleSearchService {
    private final UserReader userReader;
    private final NewsReader newsReader;
    private final ScheduleReader scheduleReader;
    private final ScheduleParticipantReader scheduleParticipantReader;
    private final ContactInterestReader contactInterestReader;

    public ScheduleDetail getScheduleDetails(Long userId, Long scheduleId) {
        Schedules schedules = scheduleReader.getSchedule(userId, scheduleId);
        List<Contacts> participants = scheduleParticipantReader.findScheduleParticipants(scheduleId);

        return ScheduleDetail.of(schedules, participants);
    }

    public List<ScheduleDetail> searchNearTermSchedules(
            int size, Long userId, boolean isUpcoming, LocalDateTime baseDateTime) {
        User user = userReader.findById(userId);
        List<Schedules> schedules =
                scheduleReader.findUserNearTermSchedules(size, user, isUpcoming, baseDateTime);
        return schedules.stream()
                .map(
                        schedule -> {
                            List<Contacts> participants =
                                    scheduleParticipantReader.findScheduleParticipants(schedule.id());
                            return ScheduleDetail.of(schedule, participants);
                        })
                .toList();
    }

    public GetRecommendationParam getUpcomingRecommendation(
            Long userId, LocalDateTime baseDateTime, int size) {
        User user = userReader.findById(userId);
        List<Schedules> schedules =
                scheduleReader.findUserNearTermSchedules(1, user, true, baseDateTime);

        if (schedules.isEmpty()) return null;
        Schedules upComingSchedule = schedules.get(0);
        List<Contacts> participants =
                scheduleParticipantReader.findScheduleParticipants(upComingSchedule.id());
        List<List<Tag>> contactTagList =
                participants.stream().map(contactInterestReader::findAllByContactOrderByCreatedAt).toList();

        URL returnImageUrl =
                upComingSchedule.participantsNum() == 1 ? participants.get(0).profileImgUrl() : null;
        List<Tag> recommendedTags = getRecommendedTags(contactTagList);
        List<TagSpecificNews> newsList = getRecommendedNews(size, recommendedTags);

        return new GetRecommendationParam(upComingSchedule, returnImageUrl, newsList);
    }

    public static List<Tag> getRecommendedTags(List<List<Tag>> contactTagList) {
        List<Tag> recommendedTags;
        if (contactTagList.size() == 1) {
            recommendedTags = contactTagList.get(0).subList(0, Math.min(5, contactTagList.get(0).size()));
        } else {
            List<Tag> keySet = getTagIdsByMostInterests(contactTagList);
            recommendedTags = keySet.subList(0, Math.min(5, contactTagList.get(0).size()));
        }
        return recommendedTags;
    }

    public List<TagSpecificNews> getRecommendedNews(int size, List<Tag> recommendedTags) {
        List<TagSpecificNews> newsList =
                new ArrayList<>(
                        recommendedTags.stream()
                                .map(tag -> new TagSpecificNews(List.of(tag), CursorPaginationResult.initial()))
                                .toList());
        if (!recommendedTags.isEmpty()) {
            newsList.remove(0);
            newsList.add(
                    0,
                    new TagSpecificNews(
                            List.of(recommendedTags.get(0)),
                            newsReader.findAllByTagIdWithCursor(
                                    size, Collections.singletonList(recommendedTags.get(0).getId()), null)));
        }
        return newsList;
    }

    public static List<Tag> getTagIdsByMostInterests(List<List<Tag>> contactTagList) {
        Map<Tag, Integer> ht = new HashMap<>();
        for (List<Tag> taglist : contactTagList) {
            for (Tag tag : taglist) {
                ht.put(tag, ht.getOrDefault(tag, 1));
            }
        }
        List<Tag> keySet = new ArrayList<>(ht.keySet());
        keySet.sort(
                new Comparator<Tag>() {
                    @Override
                    public int compare(Tag o1, Tag o2) {
                        return ht.get(o1).compareTo(ht.get(o2));
                    }
                });
        return keySet;
    }

    public List<ScheduleDetail> searchScheduleByContactAndDateRange(
            Long userId, Long contactId, int size, LocalDateTime from, LocalDateTime to) {
        List<Schedules> schedules =
                scheduleReader.findScheduleByContactIdAndDateRange(userId, contactId, size, from, to);

        return schedules.stream()
                .map(
                        schedule -> {
                            List<Contacts> participants =
                                    scheduleParticipantReader.findScheduleParticipants(schedule.id());
                            return ScheduleDetail.of(schedule, participants);
                        })
                .toList();
    }
}
