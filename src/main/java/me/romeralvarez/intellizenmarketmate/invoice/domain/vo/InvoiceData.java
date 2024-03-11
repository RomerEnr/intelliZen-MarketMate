package me.romeralvarez.intellizenmarketmate.invoice.domain.vo;


import lombok.Getter;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.SupermarketId;

import java.math.BigDecimal;
import java.util.List;


public record InvoiceData(SupermarketId supermarketId, TotalAmount totalAmount, List<LineItem> lineItems) {
  @Override
  public SupermarketId supermarketId() {
    return supermarketId;
  }

  @Override
  public TotalAmount totalAmount() {
    return totalAmount;
  }

  @Override
  public List<LineItem> lineItems() {
    return lineItems;
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

    if (calculatedTotal.compareTo(totalAmount.value()) != 0) {
      throw new IllegalArgumentException("Total amount does not match the sum of line items");
    }
  }
}
