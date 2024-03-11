package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceErrorType;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceValidationError;

import java.math.BigDecimal;

public record ItemTotalPrice(BigDecimal value) {
  public static Either<InvoiceValidationError, ItemTotalPrice> create(BigDecimal value) {
    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      return Either.left(new InvoiceValidationError("Total price must be a positive value", InvoiceErrorType.NEGATIVE_TOTAL_PRICE));
    }
    return Either.right(new ItemTotalPrice(value));
  }

  public BigDecimal getValue() {
    return value;
  }

  @Override
  public BigDecimal value() {
    return value;
  }
}