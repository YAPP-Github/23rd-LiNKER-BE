package com.imlinker.crawler;

import com.imlinker.domain.common.URL;
import com.imlinker.domain.news.CreateNewsParam;
import com.imlinker.domain.news.NewsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncCrawlerService {
    @Value("${spring.crawler.url}")
    private String baseUrl;

    @Value("${spring.crawler.class.section}")
    private String sectionClass;

    @Value("${spring.crawler.class.title}")
    private String titleClass;

    @Value("${spring.crawler.class.news}")
    private String newsUrlClass;

    @Value("${spring.crawler.class.provider}")
    private String newsProviderClass;

    @Value("${spring.crawler.class.thumb}")
    private String thumbUrlClass;

    private final NewsService newsService;

    @Async
    protected void crawlingTag(String section, Long tagId) throws IOException {
        String currentUrl = baseUrl + section;
        Document document = Jsoup.connect(currentUrl).get();

        Elements contents = document.getElementsByClass(sectionClass);
        List<CreateNewsParam> createNewsParamList = new ArrayList<>();

        for (Element content : contents) {
            String title = content.getElementsByClass(titleClass).text();
            String newsUrl = content.getElementsByClass(newsUrlClass).attr("href");
            String newsProvider = content.getElementsByClass(newsProviderClass).text();
            String thumbUrl = content.getElementsByClass(thumbUrlClass).attr("data-src");

            log.info(
                    "[뉴스 크롤링] tagId: {}, title: {}, thumbnailUrl: {}, newsUrl: {}, newsProvider: {}",
                    tagId,
                    title,
                    thumbUrl,
                    newsUrl,
                    newsProvider);
            createNewsParamList.add(
                    new CreateNewsParam(tagId, title, thumbUrl, URL.of(newsUrl), newsProvider));
        }
        newsService.create(createNewsParamList);
    }
}
