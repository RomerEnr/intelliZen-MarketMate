package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class InvoiceId {
  private final UUID uuid;

  public InvoiceId(UUID value) {
    this.uuid = value;
  }

  public static InvoiceId withId(String id) {
    return new InvoiceId(UUID.fromString(id));
  }

  public static InvoiceId withoutId() {
    return new InvoiceId(UUID.randomUUID());
  }
}
