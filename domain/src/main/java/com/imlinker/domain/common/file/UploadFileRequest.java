package com.imlinker.domain.common.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UploadFileRequest {
    private final String fileName;
    private final FileType fileType;
    private final MultipartFile file;
}
