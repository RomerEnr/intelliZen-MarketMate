package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceErrorType;
import me.romeralvarez.intellizenmarketmate.invoice.domain.errors.InvoiceValidationError;

import java.math.BigDecimal;

public record LineItem(ItemName name, ItemQuantity quantity, ItemUnitPrice unitPrice, ItemTotalPrice totalPrice) {
  public static Either<InvoiceValidationError, LineItem> create(ItemName name, ItemQuantity quantity, ItemUnitPrice unitPrice, ItemTotalPrice totalPrice) {
    return validateTotalPrice(quantity, unitPrice, totalPrice)
        .map(__ -> new LineItem(name, quantity, unitPrice, totalPrice));
  }

  private static Either<InvoiceValidationError, Void> validateTotalPrice(ItemQuantity quantity, ItemUnitPrice unitPrice, ItemTotalPrice totalPrice) {
    BigDecimal expectedTotalPrice = quantity.getValue().multiply(unitPrice.getValue());
    if (expectedTotalPrice.compareTo(totalPrice.getValue()) != 0) {
      return Either.left(new InvoiceValidationError("Total price does not match the expected value", InvoiceErrorType.TOTAL_PRICE_MISMATCH));
    }
    return Either.right(null);
  }

  public ItemTotalPrice getTotalPrice() {
    return totalPrice;
  }
}