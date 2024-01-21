package com.imlinker.crawler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crawler")
public class CrawlerController {

    @PostMapping
    public String crawling() {
        return "test";
    }
}
