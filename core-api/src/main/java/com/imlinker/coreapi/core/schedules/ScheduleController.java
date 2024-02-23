package com.imlinker.coreapi.core.schedules;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContext;
import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.coreapi.core.schedules.reponse.GetScheduleResponse;
import com.imlinker.coreapi.core.schedules.reponse.GetUpComingScheduleRecommendationResponse;
import com.imlinker.coreapi.core.schedules.reponse.GetUpComingSchedulesResponse;
import com.imlinker.coreapi.core.schedules.reponse.SearchSchedulesResponse;
import com.imlinker.coreapi.core.schedules.request.CreateScheduleRequest;
import com.imlinker.coreapi.core.schedules.request.NearTermSearchType;
import com.imlinker.coreapi.core.schedules.request.UpdateScheduleRequest;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.schedules.*;
import com.imlinker.domain.schedules.model.ScheduleDetail;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
@Tag(name = "Schedule API", description = "일정 관련 API")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleSearchService scheduleSearchService;

    @GetMapping("/near-term")
    @Operation(summary = "현재시점에서 가까운 지나갔거나 다가오는 일정 가져오기")
    public ApiResponse<GetUpComingSchedulesResponse.Schedules> getNearTermSchedules(
            @RequestParam int limit,
            @RequestParam NearTermSearchType type,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        List<ScheduleDetail> schedules =
                scheduleSearchService.searchNearTermSchedules(
                        limit, userContext.getId(), type == NearTermSearchType.UPCOMING, LocalDateTime.now());

        return ApiResponse.success(
                new GetUpComingSchedulesResponse.Schedules(
                        schedules.stream()
                                .map(GetUpComingSchedulesResponse.SimpleSchedule::fromScheduleDetail)
                                .toList()));
    }

    @GetMapping("/search")
    @Operation(summary = "일정 검색하기")
    public ApiResponse<SearchSchedulesResponse.Schedules> searchSchedules(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
            @RequestParam int limit,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {

        List<ScheduleDetail> schedules =
                scheduleSearchService.searchScheduleByDateRange(userContext.getId(), limit, from, to);
        List<SearchSchedulesResponse.SimpleSchedule> response =
                schedules.stream().map(SearchSchedulesResponse.SimpleSchedule::fromScheduleDetail).toList();

        return ApiResponse.success(new SearchSchedulesResponse.Schedules(response));
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "일정 상세 가져오기")
    public ApiResponse<GetScheduleResponse> getSchedule(
            @PathVariable Long scheduleId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        ScheduleDetail scheduleDetail =
                scheduleSearchService.getScheduleDetails(userContext.getId(), scheduleId);

        return ApiResponse.success(GetScheduleResponse.fromScheduleDetail(scheduleDetail));
    }

    @GetMapping("/contacts/{contactId}")
    @Operation(summary = "연락처기반 일정 검색하기")
    public ApiResponse<SearchSchedulesResponse.Schedules> getContactSchedules(
            @PathVariable Long contactId,
            @RequestParam int limit,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {

        List<ScheduleDetail> schedules =
                scheduleSearchService.searchScheduleByContactAndDateRange(
                        userContext.getId(), contactId, limit, from, to);
        List<SearchSchedulesResponse.SimpleSchedule> response =
                schedules.stream().map(SearchSchedulesResponse.SimpleSchedule::fromScheduleDetail).toList();

        return ApiResponse.success(new SearchSchedulesResponse.Schedules(response));
    }

    @PostMapping
    @Operation(summary = "일정 생성하기")
    public ApiResponse<OperationResult> createSchedule(
            @Valid @RequestBody CreateScheduleRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        CreateScheduleParam param = request.toParam(userContext.getId());
        OperationResult result = scheduleService.create(param);

        return ApiResponse.success(result);
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "일정 수정하기")
    public ApiResponse<OperationResult> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {

        UpdateScheduleParam param = request.toParam(userContext.getId(), scheduleId);
        OperationResult result = scheduleService.update(param);

        return ApiResponse.success(result);
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "일정 삭제하기")
    public ApiResponse<OperationResult> deleteSchedule(
            @PathVariable Long scheduleId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        OperationResult result = scheduleService.delete(scheduleId, userContext.getId());
        return ApiResponse.success(result);
    }

    @GetMapping("/upcoming/recommendation")
    @Operation(summary = "다가오는 일정 추천")
    public ApiResponse<GetUpComingScheduleRecommendationResponse.Schedule>
            getUpComingScheduleRecommendation(
                    @RequestParam(defaultValue = "20") int size,
                    @AuthenticatedUserContext AuthenticatedUserContextHolder userContextHolder) {
        GetRecommendationParam upcomingRecommendation =
                scheduleSearchService.getUpcomingRecommendation(
                        userContextHolder.getId(), LocalDateTime.now(), size);

        GetUpComingScheduleRecommendationResponse.Schedule response =
                upcomingRecommendation == null
                        ? null
                        : new GetUpComingScheduleRecommendationResponse.Schedule(
                                upcomingRecommendation.schedules().id(),
                                upcomingRecommendation.schedules().title(),
                                upcomingRecommendation.profileImgUrl().getValue(),
                                upcomingRecommendation.schedules().startDateTime(),
                                upcomingRecommendation.schedules().endDateTime(),
                                upcomingRecommendation.schedules().participants().get(0).name()
                                        + (upcomingRecommendation.schedules().participants().size() > 1
                                                ? String.format(
                                                        " 외 %d명", upcomingRecommendation.schedules().participants().size() - 1)
                                                : ""),
                                upcomingRecommendation.newsList().stream()
                                        .map(
                                                GetUpComingScheduleRecommendationResponse.Recommendation
                                                        ::fromTagSpecificNews)
                                        .toList());
        return ApiResponse.success(response);
    }
}
