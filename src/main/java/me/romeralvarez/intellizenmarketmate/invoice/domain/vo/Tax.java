package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

public class Tax {
  private final TaxRate taxRate;

  public Tax(TaxRate taxRate) {
    this.taxRate = taxRate;
  }
}
