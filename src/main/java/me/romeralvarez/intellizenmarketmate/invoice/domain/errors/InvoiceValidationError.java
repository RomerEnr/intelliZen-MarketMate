package me.romeralvarez.intellizenmarketmate.invoice.domain.errors;

import me.romeralvarez.intellizenmarketmate.shared.domain.DomainError;

public class InvoiceValidationError extends DomainError {
  private final InvoiceErrorType errorType;

  public InvoiceValidationError(String message, InvoiceErrorType errorType) {
    super(message);
    this.errorType = errorType;
  }

  public InvoiceErrorType getErrorType() {
    return errorType;
  }
}
