package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import lombok.Getter;

import java.util.UUID;

@Getter
public class InvoiceId {
  private final UUID uuid;

  public InvoiceId(UUID value) {
    this.uuid = value;
  }

  public static InvoiceId withoutId() {
    return new InvoiceId(UUID.randomUUID());
  }
}
