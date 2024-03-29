package com.imlinker.coreapi.core.news;

import com.imlinker.coreapi.core.news.response.GetNewsResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.news.NewsService;
import com.imlinker.domain.news.TagSpecificNews;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "News API", description = "뉴스 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    @Operation(summary = "태그에 맞는 뉴스 가져오기 (pagination)")
    public ApiResponse<GetNewsResponse.Recommendations> getNews(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam List<Long> tagIds,
            @RequestParam(required = false) Long cursorId) {
        List<TagSpecificNews> tagSpecificNewsList =
                newsService.findAllByTagIdWithCursor(size, tagIds, cursorId);

        GetNewsResponse.Recommendations recommendations =
                new GetNewsResponse.Recommendations(
                        tagSpecificNewsList.stream()
                                .map(GetNewsResponse.Recommendation::fromTagSpecificNews)
                                .toList());

        return ApiResponse.success(recommendations);
    }

    @GetMapping("/trend")
    @Operation(summary = "트랜드 핫이슈")
    public ApiResponse<GetNewsResponse.Recommendations> getTrendNews(
            @RequestParam(defaultValue = "20") int size) {
        List<TagSpecificNews> tagSpecificNewsList =
                newsService.findAllByTagIdWithCursor(size, List.of(), null);

        GetNewsResponse.Recommendations recommendations =
                new GetNewsResponse.Recommendations(
                        tagSpecificNewsList.stream()
                                .map(GetNewsResponse.Recommendation::fromTagSpecificNews)
                                .toList());

        return ApiResponse.success(recommendations);
    }
}
