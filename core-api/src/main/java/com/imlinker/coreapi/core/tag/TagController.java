package com.imlinker.coreapi.core.tag;

import com.imlinker.coreapi.support.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Tag API", description = "관심사 태그 관련 API")
@RequestMapping("/api/v1/tags")
public class TagController {

    @GetMapping
    @Operation(summary = "모든 관심사 태그 가져오기")
    public ApiResponse<List<com.imlinker.domain.tag.Tag>> getTags() {
        return ApiResponse.success(
                List.of(
                        new com.imlinker.domain.tag.Tag(1L, "스포츠"),
                        new com.imlinker.domain.tag.Tag(2L, "비즈니스"),
                        new com.imlinker.domain.tag.Tag(3L, "경제"),
                        new com.imlinker.domain.tag.Tag(4L, "IT")));
    }
}
