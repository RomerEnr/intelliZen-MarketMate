package me.romeralvarez.intellizenmarketmate.invoice.infrastructure.http;

import me.romeralvarez.intellizenmarketmate.invoice.application.InvoiceFileUploader;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceFileUploadFailed;
import me.romeralvarez.intellizenmarketmate.shared.domain.AttributesConstants;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/invoices")
public class InvoiceFileUploadController {
  private final InvoiceFileUploader uploadInvoiceFileUseCase;

  public InvoiceFileUploadController(InvoiceFileUploader uploadInvoiceFileUseCase) {
    this.uploadInvoiceFileUseCase = uploadInvoiceFileUseCase;
  }

  @PreAuthorize("hasRole('USER')")
  @PostMapping("/files")
  public ResponseEntity<Void> uploadInvoiceFile(
      @RequestParam("file") MultipartFile file,
      @RequestAttribute(AttributesConstants.USER_ID) UserId userId
  ) {
    try {
      uploadInvoiceFileUseCase.upload(file, userId);
      return ResponseEntity.ok().build();
    } catch (InvoiceFileUploadFailed e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }
}
