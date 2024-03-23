package me.romeralvarez.intellizenmarketmate.invoice.domain.events;

import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

public class InvoiceCreated extends DomainEvent {
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public InvoiceCreated(String aggregateId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public InvoiceCreated(String aggregateId, String eventId, String occurredOn, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId, eventId, occurredOn);
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
        LocalDateTime.parse((String) body.get("createdAt")),
        LocalDateTime.parse((String) body.get("updatedAt"))
    );
  }
}
