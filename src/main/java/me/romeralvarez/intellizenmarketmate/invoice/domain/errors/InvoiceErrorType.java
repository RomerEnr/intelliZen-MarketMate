package me.romeralvarez.intellizenmarketmate.invoice.domain.errors;

public enum InvoiceErrorType {
  TOTAL_AMOUNT_MISMATCH,
  NEGATIVE_QUANTITY,
  NEGATIVE_TOTAL_PRICE,
  NEGATIVE_UNIT_PRICE,
  TOTAL_PRICE_MISMATCH
}
