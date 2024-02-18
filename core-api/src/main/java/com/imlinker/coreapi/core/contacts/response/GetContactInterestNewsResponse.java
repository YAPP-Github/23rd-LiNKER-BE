package com.imlinker.coreapi.core.contacts.response;

import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.pagination.CursorPaginationResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetContactInterestNewsResponse {
    @Schema(description = "관심사 및 뉴스")
    public record Recommendations(@Schema(description = "추천") List<Recommendation> recommendations) {}

    @Schema(description = "관심사 및 뉴스")
    public record Recommendation(
            @Schema(description = "관심사") List<Tag> tags,
            @Schema(description = "뉴스 List") CursorPaginationResult<SimpleNews> newsList) {

        public static Recommendation fromTagSpecificNews(TagSpecificNews tagSpecificNews) {
            return new Recommendation(
                    tagSpecificNews.tags(), tagSpecificNews.newsList().transform(SimpleNews::fromNews));
        }
    }

    @Schema(description = "뉴스")
    public record SimpleNews(
            @Schema(description = "식별자") Long id,
            @Schema(description = "제목") String title,
            @Schema(description = "뉴스 제공자") String newsProvider,
            @Schema(description = "뉴스 URL") String newsUrl,
            @Schema(description = "섬네일 이미지 URL") String thumbnailUrl) {

        public static SimpleNews fromNews(News news) {
            return new SimpleNews(
                    news.getId(),
                    news.getTitle(),
                    news.getNewsProvider(),
                    news.getNewsUrl().getValue(),
                    news.getThumbnailUrl());
        }
    }
}
