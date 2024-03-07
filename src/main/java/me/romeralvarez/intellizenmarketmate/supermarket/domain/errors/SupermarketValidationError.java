package me.romeralvarez.intellizenmarketmate.supermarket.domain.errors;

import me.romeralvarez.intellizenmarketmate.shared.domain.DomainError;

public class SupermarketValidationError extends DomainError {
  private final SupermarketErrorType errorType;

  public SupermarketValidationError(String message, SupermarketErrorType errorType) {
    super(message);
    this.errorType = errorType;
  }

  public SupermarketErrorType getErrorType() {
    return errorType;
  }
}
