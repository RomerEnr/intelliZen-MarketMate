package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;

import java.math.BigDecimal;

public class Tax {
  private final BigDecimal value;


  public Tax(BigDecimal value) {
    this.value = value;
  }
  public BigDecimal value() {
    return value;
  }
}
