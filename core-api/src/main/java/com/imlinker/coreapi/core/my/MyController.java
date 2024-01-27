package com.imlinker.coreapi.core.my;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContext;
import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.coreapi.core.my.request.UpdateMyInfoRequest;
import com.imlinker.coreapi.core.my.response.GetMyResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.user.UserService;
import com.imlinker.domain.user.model.MyProfile;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/my")
@Tag(name = "My API", description = "내 정보 관련 API")
public class MyController {

    private final UserService service;

    @GetMapping
    @Operation(summary = "내 정보 가져오기")
    public ApiResponse<GetMyResponse> getMyProfile(
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        MyProfile profile = service.getMyProfile(userContext.getId());
        return ApiResponse.success(GetMyResponse.of(profile));
    }

    @PutMapping
    @Operation(summary = "내 정보 수정하기")
    public ApiResponse<OperationResult> updateMyInfo(@RequestBody UpdateMyInfoRequest request) {
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
