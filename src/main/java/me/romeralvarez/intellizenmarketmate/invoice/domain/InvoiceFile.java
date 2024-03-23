package me.romeralvarez.intellizenmarketmate.invoice.domain;

import me.romeralvarez.intellizenmarketmate.invoice.domain.events.InvoiceFileUploaded;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.ImageUrl;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceFileId;
import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.InvoiceId;
import me.romeralvarez.intellizenmarketmate.shared.domain.AggregateRoot;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

public class InvoiceFile extends AggregateRoot {
  private final InvoiceFileId id;
  private final InvoiceId invoiceId;
  private final UserId userId;
  private final ImageUrl imageUrl;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public InvoiceFile(InvoiceFileId id, UserId userId, ImageUrl imageUrl, InvoiceId invoiceId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.imageUrl = imageUrl;
    this.invoiceId = invoiceId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static InvoiceFile create(InvoiceFileId id, UserId userId, ImageUrl imageUrl, InvoiceId invoiceId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    InvoiceFile invoiceFile = new InvoiceFile(id, userId, imageUrl, invoiceId, createdAt, updatedAt);
    invoiceFile.record(new InvoiceFileUploaded(id.toString(), userId, imageUrl, createdAt, updatedAt));
    return invoiceFile;
  }
}
