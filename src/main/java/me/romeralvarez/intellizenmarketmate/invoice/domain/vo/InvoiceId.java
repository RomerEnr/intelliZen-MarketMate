package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import java.util.UUID;

public class InvoiceId {
  private final UUID value;

  public InvoiceId(UUID value) {
    this.value = value;
  }

  public static InvoiceId withoutId() {
    return new InvoiceId(UUID.randomUUID());
  }

  public UUID getValue() {
    return value;
  }
}
