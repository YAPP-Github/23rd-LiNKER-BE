package com.imlinker.crawler;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crawler")
public class CrawlerController {
    private final CrawlerService crawlerService;

    @GetMapping
    public String crawling() throws IOException {
        crawlerService.getNewsDatas();
        return "test";
    }
}
