package com.imlinker.coreapi.core.tag;

import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.tag.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
@Tag(name = "Tag API", description = "관심사 태그 관련 API")
public class TagController {

    private final TagService tagService;

    @GetMapping
    @Operation(summary = "모든 관심사 태그 가져오기")
    public ApiResponse<List<com.imlinker.domain.tag.Tag>> getTags() {
        return ApiResponse.success(tagService.getAll());
    }
}
