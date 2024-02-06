package com.imlinker.crawler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.crawler")
public class CrawlerProperties {
    private String url;

    private String section;

    private String title;

    private String news;

    private String provider;

    private String thumb;
}
