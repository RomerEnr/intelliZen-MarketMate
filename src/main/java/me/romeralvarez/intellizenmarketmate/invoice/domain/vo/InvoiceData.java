package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import me.romeralvarez.intellizenmarketmate.shared.domain.vo.SupermarketId;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceData {
  private final SupermarketId supermarketId;
  private final TotalAmount totalAmount;
  private final List<LineItem> lineItems;

  public InvoiceData(SupermarketId supermarketId, TotalAmount totalAmount, List<LineItem> lineItems) {
    this.supermarketId = supermarketId;
    this.totalAmount = totalAmount;
    this.lineItems = lineItems;
  }



  private TotalAmount calculateTotalAmount(List<LineItem> lineItems) {
    BigDecimal totalAmount = lineItems.stream()
        .map(LineItem::totalPrice)
        .map(ItemTotalPrice::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    return new TotalAmount(totalAmount);
  }

  private void validateTotalAmountMatchesLineItems() {
    BigDecimal calculatedTotal = lineItems.stream()
        .map(LineItem::getTotalPrice)
        .map(ItemTotalPrice::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    if (calculatedTotal.compareTo(totalAmount.getValue()) != 0) {
      throw new IllegalArgumentException("Total amount does not match the sum of line items");
    }
  }
}
