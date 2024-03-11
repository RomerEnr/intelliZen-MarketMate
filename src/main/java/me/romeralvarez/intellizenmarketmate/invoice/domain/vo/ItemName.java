package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

public record ItemName(String value) {
  public ItemName {
    validateValue(value);
  }

  private void validateValue(String value) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException("Item description must not be null or empty");
    }
  }

  @Override
  public String value() {
    return value;
  }
}
