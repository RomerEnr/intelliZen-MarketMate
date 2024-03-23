package me.romeralvarez.intellizenmarketmate.invoice.domain;

import me.romeralvarez.intellizenmarketmate.invoice.domain.events.InvoiceCreated;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.*;
import me.romeralvarez.intellizenmarketmate.shared.domain.AggregateRoot;
import me.romeralvarez.intellizenmarketmate.supermarket.domain.Supermarket;

import java.time.LocalDateTime;

public class Invoice extends AggregateRoot {
  private final InvoiceId id;
  private InvoiceData extractedData;
  private  InvoiceStatus invoiceStatus;
  private Tax tax;
  private Supermarket supermarket;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  private Invoice(InvoiceId id, InvoiceStatus invoiceStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.invoiceStatus = invoiceStatus;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static Invoice create(InvoiceId id, InvoiceStatus invoiceStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
    Invoice invoice = new Invoice(id, invoiceStatus, createdAt, updatedAt);
    invoice.record(new InvoiceCreated(id.value().toString(), createdAt, updatedAt));
    return invoice;
  }

  public void setExtractedData(InvoiceData extractedData, Tax tax, Supermarket supermarket) {
    this.extractedData = extractedData;
    this.tax = tax;
    this.supermarket = supermarket;
  }

  public InvoiceId getId() {
    return id;
  }
}
