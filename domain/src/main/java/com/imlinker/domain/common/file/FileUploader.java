package com.imlinker.domain.common.file;

import com.imlinker.domain.common.URL;

public interface FileUploader {
    URL uploadImage(UploadFileRequest request);
}
