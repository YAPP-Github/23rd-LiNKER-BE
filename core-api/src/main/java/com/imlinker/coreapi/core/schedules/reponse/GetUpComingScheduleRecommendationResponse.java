package com.imlinker.coreapi.core.schedules.reponse;

import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.news.model.News;
import com.imlinker.domain.tag.model.Tag;
import com.imlinker.pagination.CursorPaginationResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUpComingScheduleRecommendationResponse {
    public record Schedule(
            @Schema(description = "식별자") Long id,
            @Schema(description = "제목") String title,
            @Schema(description = "프로필 이미지 URL") String profileImgUrl,
            @Schema(description = "시작 날짜") LocalDateTime startDateTime,
            @Schema(description = "종료 날짜") LocalDateTime endDateTime,
            @Schema(description = "관심사 및 뉴스 List") List<Recommendation> recommendations) {}

    @Schema(description = "관심사 및 뉴스")
    public record Recommendation(
            @Schema(description = "관심사") List<Tag> tags,
            @Schema(description = "뉴스 List")
                    CursorPaginationResult<GetUpComingScheduleRecommendationResponse.SimpleNews> newsList) {

        public static GetUpComingScheduleRecommendationResponse.Recommendation fromTagSpecificNews(
                TagSpecificNews tagSpecificNews) {
            return new GetUpComingScheduleRecommendationResponse.Recommendation(
                    tagSpecificNews.tags(),
                    tagSpecificNews
                            .newsList()
                            .transform(GetUpComingScheduleRecommendationResponse.SimpleNews::fromNews));
        }
    }

    @Schema(description = "뉴스")
    public record SimpleNews(
            @Schema(description = "식별자") Long id,
            @Schema(description = "제목") String title,
            @Schema(description = "뉴스 제공자") String newsProvider,
            @Schema(description = "뉴스 URL") String newsUrl,
            @Schema(description = "섬네일 이미지 URL") String thumbnailUrl) {

        public static GetUpComingScheduleRecommendationResponse.SimpleNews fromNews(News news) {
            return new GetUpComingScheduleRecommendationResponse.SimpleNews(
                    news.getId(),
                    news.getTitle(),
                    news.getNewsProvider(),
                    news.getNewsUrl().getValue(),
                    news.getThumbnailUrl());
        }
    }
}
