package com.imlinker.domain.common.file;

import org.springframework.web.multipart.MultipartFile;

public record UploadFile(String fileName, FileType fileType, MultipartFile file) {}
