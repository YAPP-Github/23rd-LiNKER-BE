package com.imlinker.coreapi.core.my;

import com.imlinker.coreapi.core.my.request.UpdateMyInfoRequest;
import com.imlinker.coreapi.core.my.response.GetMyResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/my")
@Tag(name = "My API", description = "내 정보 관련 API")
public class MyController {

    @GetMapping
    @Operation(summary = "내 정보 가져오기")
    public ApiResponse<GetMyResponse> getMyInfo() {
        GetMyResponse response =
                new GetMyResponse(
                        "김태준",
                        "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                        "Json 상하차 담당",
                        "Yapp23기 Web1팀",
                        "rlaxowns7916@gmail.com",
                        List.of(
                                new com.imlinker.domain.common.Tag(1L, "스포츠"),
                                new com.imlinker.domain.common.Tag(2L, "게임")),
                        0,
                        0);

        return ApiResponse.success(response);
    }

    @PutMapping
    @Operation(summary = "내 정보 수정하기")
    public ApiResponse<OperationResult> updateMyInfo(@RequestBody UpdateMyInfoRequest request) {
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
