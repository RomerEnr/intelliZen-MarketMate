package me.romeralvarez.intellizenmarketmate.product.domain.vo;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductId {
  private final UUID value;

  public ProductId(UUID value) {
    this.value = value;
  }

  public static ProductId withoutId() {
    return new ProductId(UUID.randomUUID());
  }

  public UUID value() {
    return value;
  }
}
