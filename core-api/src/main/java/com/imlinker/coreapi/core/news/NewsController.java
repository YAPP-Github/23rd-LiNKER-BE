package com.imlinker.coreapi.core.news;

import com.imlinker.coreapi.core.news.response.GetNewsResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.news.GetNewsParam;
import com.imlinker.domain.news.NewsService;
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
    @Operation(summary = "태그에 맞는 뉴스 가져오기 (mock)")
    public ApiResponse<List<GetNewsResponse.Entry>> getNews(@RequestParam List<String> tag) {
        List<GetNewsResponse.Entry> entries =
                List.of(
                        new GetNewsResponse.Entry(
                                new com.imlinker.domain.tag.model.Tag(1L, "스포츠"),
                                List.of(
                                        new GetNewsResponse.SimpleNews(
                                                1L,
                                                "스포츠",
                                                "연합뉴스",
                                                "https://r.yna.co.kr/global/home",
                                                "https://r.yna.co.kr/global/home/v01/img/yonhapnews_logo_600x600_kr01.jpg\"")),
                                1L));

        return ApiResponse.success(entries);
    }

    @GetMapping("/profile")
    @Operation(summary = "지인 프로필 - 태그에 맞는 뉴스 가져오기 (pagination)")
    public ApiResponse<GetNewsResponse.Entry> getProfileNews(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam Long tagId,
            @RequestParam(required = false) Long cursorId) {
        GetNewsParam getNewsParam = newsService.findAllByTagIdWithCursor(size, tagId, cursorId);

        GetNewsResponse.Entry entry =
                new GetNewsResponse.Entry(
                        getNewsParam.tag(),
                        getNewsParam.news().stream().map(GetNewsResponse.SimpleNews::fromNews).toList(),
                        getNewsParam.nextCursor());

        return ApiResponse.success(entry);
    }
}
