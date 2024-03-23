package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import java.util.UUID;

public record InvoiceFileId(UUID value) {

  public static InvoiceFileId withoutId() {
    return new InvoiceFileId(UUID.randomUUID());
  }
}
