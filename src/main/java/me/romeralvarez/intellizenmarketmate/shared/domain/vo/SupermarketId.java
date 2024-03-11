package me.romeralvarez.intellizenmarketmate.shared.domain.vo;

import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductId;

import java.util.UUID;

public class SupermarketId {
  private final UUID value;

  public SupermarketId(UUID value) {
    this.value = value;
  }

  public static ProductId withoutId() {
    return new ProductId(UUID.randomUUID());
  }
}
