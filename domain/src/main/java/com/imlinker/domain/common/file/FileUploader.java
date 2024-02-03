package com.imlinker.domain.common.file;

import com.imlinker.domain.common.model.URL;

public interface FileUploader {
    URL uploadImage(UploadFile file);
}
