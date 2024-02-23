package com.imlinker.domain.schedules;

import com.imlinker.domain.common.model.URL;
import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import java.util.List;

public record GetRecommendationParam(
        ScheduleDetail schedules, URL profileImgUrl, List<TagSpecificNews> newsList) {}
