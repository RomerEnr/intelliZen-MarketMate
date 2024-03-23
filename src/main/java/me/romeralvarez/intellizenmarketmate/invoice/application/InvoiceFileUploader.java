package me.romeralvarez.intellizenmarketmate.invoice.application;

import me.romeralvarez.intellizenmarketmate.invoice.domain.Invoice;
import me.romeralvarez.intellizenmarketmate.invoice.domain.InvoiceFile;
import me.romeralvarez.intellizenmarketmate.invoice.domain.repository.InvoiceFileRepository;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceFileUploadFailed;
import me.romeralvarez.intellizenmarketmate.invoice.domain.repository.InvoiceRepository;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.ImageUrl;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceFileId;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceId;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceStatus;
import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.EventBus;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.files.FileUploader;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceFileUploader {
  private final InvoiceFileRepository invoiceFileRepository;
  private final InvoiceRepository invoiceRepository;
  private final FileUploader fileUploader;
  private final EventBus eventBus;

  public InvoiceFileUploader(InvoiceFileRepository invoiceFileRepository, InvoiceRepository invoiceRepository, FileUploader fileUploader, EventBus eventBus) {
    this.invoiceFileRepository = invoiceFileRepository;
    this.invoiceRepository = invoiceRepository;
    this.fileUploader = fileUploader;
    this.eventBus = eventBus;
  }

  public void upload(MultipartFile file, UserId userId) {
    String fileName = file.getOriginalFilename();
    try {
      String fileUrl = uploadFileToStorage(file, userId);
      LocalDateTime now = LocalDateTime.now();

      Invoice provisionalInvoice = createAndPersistProvisionalInvoice(userId, now);
      InvoiceFile invoiceFile = createAndPersistInvoiceFile(fileUrl, userId, provisionalInvoice.getId(), now);

      publishInvoiceFileEvents(invoiceFile);
    } catch (IOException e) {
      throw new InvoiceFileUploadFailed(fileName);
    }
  }

  private String uploadFileToStorage(MultipartFile file, UserId userId) throws IOException {
    Map<String, String> metadata = createFileMetadata(userId);
    return fileUploader.upload(file.getOriginalFilename(), file.getInputStream(), metadata);
  }

  private Map<String, String> createFileMetadata(UserId userId) {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("userId", userId.value().toString());
    return metadata;
  }

  private Invoice createAndPersistProvisionalInvoice(UserId userId, LocalDateTime timestamp) {
    Invoice provisionalInvoice = Invoice.create(InvoiceId.withoutId(), userId, InvoiceStatus.PENDING, timestamp, timestamp);
    provisionalInvoice.setStatus(InvoiceStatus.PENDING);
    invoiceRepository.save(provisionalInvoice);
    return provisionalInvoice;
  }

  private InvoiceFile createAndPersistInvoiceFile(String fileUrl, UserId userId, InvoiceId invoiceId, LocalDateTime timestamp) {
    InvoiceFile invoiceFile = InvoiceFile.create(InvoiceFileId.withoutId(), userId, new ImageUrl(fileUrl), invoiceId, timestamp, timestamp);
    invoiceFileRepository.save(invoiceFile);
    return invoiceFile;
  }

  private void publishInvoiceFileEvents(InvoiceFile invoiceFile) {
    eventBus.publish(invoiceFile.pullDomainEvents());
  }
}
