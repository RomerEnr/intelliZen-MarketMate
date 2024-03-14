package me.romeralvarez.intellizenmarketmate.product.domain.events;

import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ProductCreated extends DomainEvent {
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public ProductCreated(String aggregateId, String name, String description, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId);
    this.name = name;
    this.description = description;
    this.price = price;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public ProductCreated(String aggregateId, String eventId, String occurredOn, String name, String description, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
    super(aggregateId, eventId, occurredOn);
    this.name = name;
    this.description = description;
    this.price = price;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public String eventName() {
    return "product.created";
  }

  @Override
  public HashMap<String, Serializable> toPrimitives() {
    return new HashMap<String, Serializable>() {{
      put("name", name);
      put("description", description);
      put("price", price);
      put("createdAt", createdAt.toString());
      put("updatedAt", updatedAt.toString());
    }};
  }

  @Override
  public ProductCreated fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
    return new ProductCreated(
        aggregateId,
        eventId,
        occurredOn,
        (String) body.get("name"),
        (String) body.get("description"),
        (BigDecimal) body.get("price"),
        LocalDateTime.parse((String) body.get("createdAt")),
        LocalDateTime.parse((String) body.get("updatedAt"))
    );
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
