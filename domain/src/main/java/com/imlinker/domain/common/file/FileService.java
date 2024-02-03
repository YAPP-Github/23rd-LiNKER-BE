package com.imlinker.domain.common.file;

import com.imlinker.domain.common.model.URL;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileUploader fileUploader;

    public URL uploadImage(Long userId, MultipartFile file) {
        String fileName = String.format("user:%s-profile-%s", userId, LocalDateTime.now());
        UploadFile uploadFile = new UploadFile(fileName, FileType.IMAGE, file);
        return fileUploader.uploadImage(uploadFile);
    }
}
