package me.romeralvarez.intellizenmarketmate.shared.infrastructure.s3;

import lombok.extern.slf4j.Slf4j;
import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.files.FileUploader;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;

@Service
@Slf4j
public class S3FileUploader implements FileUploader {
  private final S3Client s3Client;
  private final S3Presigner s3Presigner;
  private final String bucketName;

  public S3FileUploader(S3Client s3Client, S3Presigner s3Presigner, @Value("${market-mate.s3.bucket}") String bucketName) {
    this.s3Client = s3Client;
    this.s3Presigner = s3Presigner;
    this.bucketName = bucketName;
  }

  @Override
  public String upload(String fileName, InputStream inputStream, Map<String, String> metadata) {
    log.info("Uploading file to S3 with fileName: {}, metadata: {}", fileName, metadata);

    PutObjectRequest.Builder requestBuilder = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName);

    if (metadata != null && !metadata.isEmpty()) {
      requestBuilder.metadata(metadata);
    }

    PutObjectRequest request = requestBuilder.build();

    try {
      s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
      log.info("File uploaded to S3 successfully with fileName: {}", fileName);
      return getFileUrl(fileName);
    } catch (IOException e) {
      log.error("Error occurred while uploading file to S3: {}", fileName, e);
      throw new RuntimeException("Failed to upload file to S3", e);
    }
  }

  private String getFileUrl(String fileName) {
    log.info("Generating presigned URL for file: {}", fileName);

    GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(30))
        .getObjectRequest(builder -> builder.bucket(bucketName).key(fileName))
        .build();

    PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);

    String fileUrl = presignedGetObjectRequest.url().toString();

    log.info("Generated presigned URL for file: {}, URL: {}", fileName, fileUrl);

    return fileUrl;
  }

}
