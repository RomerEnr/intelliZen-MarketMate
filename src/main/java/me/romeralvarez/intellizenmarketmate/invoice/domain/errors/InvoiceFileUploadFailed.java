package me.romeralvarez.intellizenmarketmate.invoice.domain.errors;

import me.romeralvarez.intellizenmarketmate.shared.domain.DomainException;

public class InvoiceFileUploadFailed extends DomainException {
  public InvoiceFileUploadFailed(String fileName) {
    super("invoice_file_upload_failed", String.format("Failed to upload invoice file: %s", fileName));
  }
}
