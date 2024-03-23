package me.romeralvarez.intellizenmarketmate.shared.infrastructure.s3.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {
  @Value("${market-mate.s3.access-key}")
  private String accessKey;

  @Value("${market-mate.s3.secret-key}")
  private String secretKey;

  @Value("${market-mate.s3.region}")
  private String region;


  @Bean
  public S3Client s3Client() {
    AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
    return S3Client.builder()
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .build();
  }

  @Bean
  public S3Presigner s3Presigner() {
    AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
    return S3Presigner.builder()
        .region(Region.of(region))
        .credentialsProvider(StaticCredentialsProvider.create(credentials))
        .build();
  }
}
