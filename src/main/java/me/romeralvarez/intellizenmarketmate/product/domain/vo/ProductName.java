package me.romeralvarez.intellizenmarketmate.product.domain.vo;


import io.vavr.control.Either;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductErrorType;
import me.romeralvarez.intellizenmarketmate.product.domain.errors.ProductValidationError;

public record ProductName(String value) {
  public static Either<ProductValidationError, ProductName> create(String value) {
    return validateName(value)
        .map(ProductName::new);
  }

  private static Either<ProductValidationError, String> validateName(String value) {
    if (value == null || value.trim().isEmpty()) {
      return Either.left(new ProductValidationError("Product name cannot be null or empty", ProductErrorType.INVALID_PRODUCT_NAME));
    }
    return Either.right(value);
  }
}