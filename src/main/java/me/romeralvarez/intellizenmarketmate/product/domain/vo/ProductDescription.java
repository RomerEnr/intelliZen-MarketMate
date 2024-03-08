package me.romeralvarez.intellizenmarketmate.product.domain.vo;

import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductErrorType;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductValidationError;

public record ProductDescription(String value) {
  public static Either<ProductValidationError, ProductDescription> create(String value) {
    return validateDescription(value)
        .map(ProductDescription::new);
  }

  private static Either<ProductValidationError, String> validateDescription(String value) {
    if (value == null || value.trim().isEmpty()) {
      return Either.left(new ProductValidationError("Product description cannot be null or empty", ProductErrorType.INVALID_PRODUCT_DESCRIPTION));
    }
    return Either.right(value);
  }
}