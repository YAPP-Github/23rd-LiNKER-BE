package com.imlinker.coreapi.core.news.response;

import com.imlinker.domain.common.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetNewsResponse {
    @Schema(description = "관심사 및 뉴스")
    public record Entry(
            @Schema(description = "관심사") Tag tag,
            @Schema(description = "뉴스 List") List<SimpleNews> news) {}

    @Schema(description = "뉴스")
    public record SimpleNews(
            @Schema(description = "식별자") Long id,
            @Schema(description = "제목") String title,
            @Schema(description = "뉴스 제공자") String newsProvider,
            @Schema(description = "섬네일 이미지 URL") String thumbnailUrl) {}
}
