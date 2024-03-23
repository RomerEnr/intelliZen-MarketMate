package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.invoice.domain.vo.ImageUrl;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.UserId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class InvoiceFileUploaded extends DomainEvent {
  private final UserId userId;
  private final ImageUrl fileUrl;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public InvoiceFileUploaded(String aggregateId, UserId userId, ImageUrl fileUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId);
    this.userId = userId;
    this.fileUrl = fileUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public InvoiceFileUploaded(String aggregateId, String eventId, String occurredOn, UserId userId, ImageUrl fileUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId, eventId, occurredOn);
    this.userId = userId;
    this.fileUrl = fileUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String eventName() {
    return "invoice_file.uploaded";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("userId", userId.value().toString());
      put("fileUrl", fileUrl.value());
      put("createdAt", createdAt.toString());
      put("updatedAt", updatedAt.toString());
    }};
  }

  @Override
  public InvoiceFileUploaded fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    UserId userId = new UserId(UUID.fromString((String) body.get("userId")));
    ImageUrl fileUrl = new ImageUrl((String) body.get("fileUrl"));
    LocalDateTime createdAt = LocalDateTime.parse((String) body.get("createdAt"));
    LocalDateTime updatedAt = LocalDateTime.parse((String) body.get("updatedAt"));

    return new InvoiceFileUploaded(
        aggregateId,
        eventId,
        occurredOn,
        userId,
        fileUrl,
        createdAt,
        updatedAt
    );
  }

  public UserId getUserId() {
    return userId;
  }

  public ImageUrl getFileUrl() {
    return fileUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
