package com.imlinker.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.imlinker.domain.common.file.FileUploader;
import com.imlinker.domain.common.file.UploadFile;
import com.imlinker.domain.common.model.URL;
import com.imlinker.error.ApplicationException;
import com.imlinker.error.ErrorType;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileUploader implements FileUploader {

    @Value("${aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    @Override
    public URL uploadImage(UploadFile uploadFile) {
        log.info(
                "[S3][FileUpload][시작] (fileName={}, contentType={}",
                uploadFile.fileName(),
                uploadFile.fileType().getContentType());

        if (!uploadFile.fileType().isSupported(uploadFile.file().getContentType())) {
            log.info(
                    "[S3][FileUpload][실패] 지원하지 않는 이미지 파일 형식(contentType={})",
                    uploadFile.file().getContentType());
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        try {
            InputStream fis = uploadFile.file().getInputStream();
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentLength(uploadFile.file().getSize());
            metaData.setContentType(uploadFile.file().getContentType());

            s3Client.putObject(bucket, uploadFile.fileName(), fis, metaData);
            String responseUrl = s3Client.getUrl(bucket, uploadFile.fileName()).toString();

            return URL.of(responseUrl);
        } catch (Exception e) {
            log.info(
                    "[S3][FileUpload][실패] (fileName={}, contentType={}, cause={})",
                    uploadFile.fileName(),
                    uploadFile.file().getContentType(),
                    e.getMessage());
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR, null, e.getCause());
        }
    }
}
