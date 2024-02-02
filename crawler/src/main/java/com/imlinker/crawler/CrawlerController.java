package com.imlinker.crawler;

import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.enums.OperationResult;
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
    public ApiResponse<OperationResult> crawling() throws IOException {
        crawlerService.getNewsDatas();
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
