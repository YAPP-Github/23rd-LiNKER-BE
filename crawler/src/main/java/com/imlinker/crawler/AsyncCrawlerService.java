package com.imlinker.crawler;

import com.imlinker.crawler.config.CrawlerProperties;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncCrawlerService {

    private final CrawlerProperties crawlerProperties;
    private final NewsService newsService;

    @Async
    protected void crawlingTag(String section, Long tagId) throws IOException {
        String currentUrl = crawlerProperties.getUrl() + section;
        Document document = Jsoup.connect(currentUrl).get();

        Elements contents = document.getElementsByClass(crawlerProperties.getSection());
        List<CreateNewsParam> createNewsParamList = new ArrayList<>();

        for (Element content : contents) {
            String title = content.getElementsByClass(crawlerProperties.getTitle()).text();
            String newsUrl = content.getElementsByClass(crawlerProperties.getNews()).attr("href");
            String newsProvider = content.getElementsByClass(crawlerProperties.getProvider()).text();
            String thumbUrl = content.getElementsByClass(crawlerProperties.getThumb()).attr("data-src");

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
