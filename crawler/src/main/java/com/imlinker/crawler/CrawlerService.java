package com.imlinker.crawler;

import com.imlinker.domain.tag.PlatformService;
import com.imlinker.domain.tag.TagService;
import com.imlinker.domain.tag.model.Platform;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerService {

    @Value("${spring.token}")
    private String crawlerToken;

    private final TagService tagService;
    private final PlatformService platformService;
    private final AsyncCrawlerService asyncCrawlerService;

    public void getNewsDatas(String token) throws IOException {
        if (!crawlerToken.equals(token)) {
            throw new ApplicationException(ErrorType.UNAUTHENTICATED);
        }
        List<Tag> tags = tagService.getTags();

        for (Tag tag : tags) {
            Platform platform = platformService.getPlatform(tag.getPlatformId());
            asyncCrawlerService.crawlingTag(platform.section(), tag.getId());
        }
    }
}
