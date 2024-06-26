package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceErrorType;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceValidationError;

import java.math.BigDecimal;

public record TotalAmount(BigDecimal value) {
  public static Either<InvoiceValidationError, TotalAmount> create(BigDecimal value) {
    return validatePositiveValue(value)
        .map(TotalAmount::new);
  }

  private static Either<InvoiceValidationError, BigDecimal> validatePositiveValue(BigDecimal value) {
    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      return Either.left(new InvoiceValidationError("Total amount must be a positive value", InvoiceErrorType.NEGATIVE_TOTAL_PRICE));
    }
    return Either.right(value);
  }

  public BigDecimal value() {
    return value;
  }
}