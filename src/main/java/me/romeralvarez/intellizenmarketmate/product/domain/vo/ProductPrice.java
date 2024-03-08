package me.romeralvarez.intellizenmarketmate.product.domain.vo;

import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductErrorType;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductValidationError;

import java.math.BigDecimal;

public record ProductPrice(BigDecimal value) {
  public static Either<ProductValidationError, ProductPrice> create(BigDecimal value) {
    return validatePrice(value)
        .map(ProductPrice::new);
  }

  private static Either<ProductValidationError, BigDecimal> validatePrice(BigDecimal value) {
    if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
      return Either.left(new ProductValidationError("Product price must be greater than zero", ProductErrorType.INVALID_PRODUCT_PRICE));
    }
    return Either.right(value);
  }
}
