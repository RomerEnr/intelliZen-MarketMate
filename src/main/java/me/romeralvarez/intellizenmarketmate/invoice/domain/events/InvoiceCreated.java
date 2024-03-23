package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class InvoiceCreated extends DomainEvent {
  private final UUID userId;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public InvoiceCreated(UUID aggregateId, UUID userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId.toString());
    this.userId = userId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public InvoiceCreated(String aggregateId, String eventId, String occurredOn, UUID userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId, eventId, occurredOn);
    this.userId = userId;
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
      put("userId", userId.toString());
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
        UUID.fromString((String) body.get("userId")),
        LocalDateTime.parse((String) body.get("createdAt")),
        LocalDateTime.parse((String) body.get("updatedAt"))
    );
  }
}
