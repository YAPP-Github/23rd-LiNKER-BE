package com.imlinker.coreapi.core.system;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContext;
import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.coreapi.core.system.response.UploadImageResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.common.file.FileService;
import com.imlinker.domain.common.model.URL;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@Tag(name = "Image API", description = "이미지 처리 관련 API")
public class ImageController {

    private final FileService service;

    @PostMapping(
            value = "/upload",
            consumes = {MediaType.ALL_VALUE})
    @Operation(summary = "이미지 업로드하기")
    public ApiResponse<UploadImageResponse> uploadImage(
            @RequestPart MultipartFile file,
            @AuthenticatedUserContext AuthenticatedUserContextHolder authenticatedUserContextHolder) {
        URL uploadedImageUrl = service.uploadImage(authenticatedUserContextHolder.getId(), file);
        return ApiResponse.success(new UploadImageResponse(uploadedImageUrl.getValue()));
    }
}
