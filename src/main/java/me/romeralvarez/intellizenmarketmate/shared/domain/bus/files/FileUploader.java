package me.romeralvarez.intellizenmarketmate.shared.domain.bus.files;

import java.io.InputStream;
import java.util.Map;

public interface FileUploader {

  String upload(String fileName, InputStream inputStream, Map<String, String> metadata);
}
