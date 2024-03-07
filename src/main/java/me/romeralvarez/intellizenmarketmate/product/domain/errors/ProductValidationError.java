package me.romeralvarez.intellizenmarketmate.product.domain.errors;

import me.romeralvarez.intellizenmarketmate.shared.domain.DomainError;

public class ProductValidationError extends DomainError {
  private final ProductErrorType errorType;

  public ProductValidationError(String message, ProductErrorType errorType) {
    super(message);
    this.errorType = errorType;
  }

  public ProductErrorType getErrorType() {
    return errorType;
  }
}
