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

    @GetMapping("/profile")
    @Operation(summary = "태그에 맞는 뉴스 가져오기 - 지인 프로필/트렌드 핫이슈 (pagination)")
    public ApiResponse<GetNewsResponse.Entry> getProfileNews(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam List<Long> tagIds,
            @RequestParam(required = false) Long cursorId) {
        GetNewsParam getNewsParam = newsService.findAllByTagIdWithCursor(size, tagIds, cursorId);

        GetNewsResponse.Entry entry =
                new GetNewsResponse.Entry(
                        getNewsParam.tags(),
                        getNewsParam.news().stream().map(GetNewsResponse.SimpleNews::fromNews).toList(),
                        getNewsParam.nextCursor());

        return ApiResponse.success(entry);
    }
}
