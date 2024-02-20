package com.imlinker.domain.contacts;

import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.news.NewsReader;
import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.news.TagSpecificNewsListFactory;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.pagination.CursorPaginationResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactsInterestedNewsSearchManager {

    private final NewsReader newsReader;
    private final ContactInterestReader contactInterestReader;
    private final TagSpecificNewsListFactory tagSpecificNewsListFactory;

    public List<TagSpecificNews> searchContactInterestRelatedNews(Contacts contacts, int size) {
        List<Long> contactInterestTagIds =
                contactInterestReader.findAllByContact(contacts).stream().map(Tag::getId).toList();

        CursorPaginationResult<News> contactInterestRelatedNewsList =
                newsReader.findAllByTagIdWithCursor(size, contactInterestTagIds, null);

        return tagSpecificNewsListFactory.build(contactInterestTagIds, contactInterestRelatedNewsList);
    }
}
