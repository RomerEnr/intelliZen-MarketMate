package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import java.util.UUID;

public record InvoiceId(UUID value) {

  public static InvoiceId withoutId() {
    return new InvoiceId(UUID.randomUUID());
  }

  @Override
  public UUID value() {
    return value;
  }
}
