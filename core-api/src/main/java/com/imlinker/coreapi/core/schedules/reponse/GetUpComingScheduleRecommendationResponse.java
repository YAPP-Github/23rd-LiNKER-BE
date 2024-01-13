package com.imlinker.coreapi.core.schedules.reponse;

import com.imlinker.domain.common.Tag;
import java.time.LocalDateTime;
import java.util.List;

public class GetUpComingScheduleRecommendationResponse {
    public record Schedule(
            String scheduleId,
            String title,
            String profileImgUrl,
            LocalDateTime startTs,
            LocalDateTime endTs,
            List<Recommendation> recommendations) {}

    public record Recommendation(Tag tag, List<SimpleNews> contents) {}

    public record SimpleNews(Long id, String title, String newsProvider, String thumbnailUrl) {}
}
