package me.romeralvarez.intellizenmarketmate.invoice.domain;

import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.*;

import java.time.LocalDateTime;

public class Invoice {
  private final InvoiceId id;
  private InvoiceData extractedData;
  private  InvoiceStatus invoiceStatus;
  private final Tax tax;
  private final ImageUrl imageUrl;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Invoice(InvoiceId id, Tax tax, ImageUrl imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.tax = tax;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
