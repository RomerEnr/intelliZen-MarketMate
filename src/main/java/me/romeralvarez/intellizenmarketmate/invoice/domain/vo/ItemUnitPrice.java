package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceErrorType;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceValidationError;

import java.math.BigDecimal;

public record ItemUnitPrice(BigDecimal value) {
  public static Either<InvoiceValidationError, ItemUnitPrice> create(BigDecimal value) {
    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      return Either.left(new InvoiceValidationError("Unit price must be a positive value", InvoiceErrorType.NEGATIVE_UNIT_PRICE));
    }
    return Either.right(new ItemUnitPrice(value));
  }

  public BigDecimal getValue() {
    return value;
  }
}
