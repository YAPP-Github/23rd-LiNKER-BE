package com.imlinker.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.imlinker.domain.common.URL;
import com.imlinker.domain.common.file.FileUploader;
import com.imlinker.domain.common.file.UploadFileRequest;
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
public class S3Uploader implements FileUploader {

    @Value("${aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    @Override
    public URL uploadImage(UploadFileRequest request) {
        log.info(
                "[S3][FileUpload][시작] (fileName={}, contentType={}",
                request.getFileName(),
                request.getFileType().getContentType());

        if (!request.getFileType().isSupported(request.getFile().getContentType())) {
            log.info(
                    "[S3][FileUpload][실패] 지원하지 않는 이미지 파일 형식(contentType={})",
                    request.getFile().getContentType());
            throw new ApplicationException(ErrorType.INVALID_REQUEST_PARAMETER);
        }

        try {
            InputStream fis = request.getFile().getInputStream();
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentLength(request.getFile().getSize());
            metaData.setContentType(request.getFile().getContentType());

            s3Client.putObject(bucket, request.getFileName(), fis, metaData);
            String responseUrl = s3Client.getUrl(bucket, request.getFileName()).toString();

            return URL.of(responseUrl);
        } catch (Exception e) {
            log.info(
                    "[S3][FileUpload][실패] (fileName={}, contentType={}, cause={})",
                    request.getFileName(),
                    request.getFile().getContentType(),
                    e.getMessage());
            throw new ApplicationException(ErrorType.INTERNAL_PROCESSING_ERROR, null, e.getCause());
        }
    }
}
