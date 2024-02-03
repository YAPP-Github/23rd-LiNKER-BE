package com.imlinker.domain.common.file;

import com.imlinker.domain.common.model.URL;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileUploader fileUploader;

    public URL uploadImage(Long userId, MultipartFile file) {
        ImageUrlDissection dissection = ImageUrlDissection.generate(userId);
        UploadFile uploadFile = new UploadFile(dissection.toUrlFormat(), FileType.IMAGE, file);
        return fileUploader.uploadImage(uploadFile);
    }

    public void deleteImage(Long userId, List<URL> imageUrls) {
        imageUrls.forEach(
                imageUrl -> {
                    ImageUrlDissection dissection = ImageUrlDissection.fromUrl(imageUrl);
                    if (!dissection.userId().equals(userId)) {
                        throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
                    }
                    fileUploader.deleteFile(dissection.toUrlFormat());
                });
    }
}
