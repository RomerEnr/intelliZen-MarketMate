package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceErrorType;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceValidationError;

import java.math.BigDecimal;

public record ItemQuantity(BigDecimal value) {
  public static Either<InvoiceValidationError, ItemQuantity> create(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) < 0) {
      return Either.left(new InvoiceValidationError("Quantity must be a positive value", InvoiceErrorType.NEGATIVE_QUANTITY));
    }
    return Either.right(new ItemQuantity(value));
  }

  public BigDecimal getValue() {
    return value;
  }
}
