package com.imlinker.domain.common.file;

import com.imlinker.domain.common.model.URL;
import com.imlinker.enums.OperationResult;

public interface FileUploader {
    URL uploadImage(UploadFile file);

    OperationResult deleteFile(String fileName);
}
