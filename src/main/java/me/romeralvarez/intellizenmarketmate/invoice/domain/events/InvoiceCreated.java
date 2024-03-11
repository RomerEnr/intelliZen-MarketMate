package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

public class InvoiceCreated extends DomainEvent {
  private final BigDecimal tax;
  private final String imageUrl;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public InvoiceCreated(String aggregateId, BigDecimal tax, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId);
    this.tax = tax;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public InvoiceCreated(String aggregateId, String eventId, String occurredOn, BigDecimal tax, String imageUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId, eventId, occurredOn);
    this.tax = tax;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String eventName() {
    return "invoice.created";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("tax", tax);
      put("imageUrl", imageUrl);
      put("createdAt", createdAt.toString());
      put("updatedAt", updatedAt.toString());
    }};
  }

  @Override
  public InvoiceCreated fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    return new InvoiceCreated(
        aggregateId,
        eventId,
        occurredOn,
        (BigDecimal) body.get("tax"),
        (String) body.get("imageUrl"),
        LocalDateTime.parse((String) body.get("createdAt")),
        LocalDateTime.parse((String) body.get("updatedAt"))
    );
  }
}
