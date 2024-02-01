package com.imlinker.coreapi.core.tag;

import com.imlinker.coreapi.core.tag.request.CreateTagRequest;
import com.imlinker.coreapi.core.tag.response.GetTagsResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.tag.TagService;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
@Tag(name = "Tag API", description = "관심사 태그 관련 API")
public class TagController {
    private final TagService tagService;

    @GetMapping
    @Operation(summary = "모든 관심사 태그 가져오기")
    public ApiResponse<GetTagsResponse> getTags() {
        List<com.imlinker.domain.tag.model.Tag> tags = tagService.getTags();
        GetTagsResponse getTagsResponse = new GetTagsResponse(tags);
        return ApiResponse.success(getTagsResponse);
    }

    @PostMapping
    @Operation(summary = "태그 등록")
    public ApiResponse<OperationResult> createTag(@RequestBody CreateTagRequest createTagRequest) {
        tagService.create(createTagRequest.toParam());
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
