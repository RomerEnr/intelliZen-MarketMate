package me.romeralvarez.intellizenmarketmate.invoice.domain;

import me.romeralvarez.intellizenmarketmate.invoice.domain.events.InvoiceCreated;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.*;
import me.romeralvarez.intellizenmarketmate.shared.domain.AggregateRoot;

import java.time.LocalDateTime;

public class Invoice extends AggregateRoot {
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

  public static Invoice create(InvoiceId id, Tax tax, ImageUrl imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    Invoice invoice = new Invoice(id, tax, imageUrl, createdAt, updatedAt);
    invoice.record(new InvoiceCreated(id.getValue().toString(), tax.value(), imageUrl.value(), createdAt, updatedAt));
    return invoice;
  }
}
