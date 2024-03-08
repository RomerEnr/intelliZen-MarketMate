package me.romeralvarez.intellizenmarketmate.product.domain.vo;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductId {
  private final UUID uuid;

  public ProductId(UUID value) {
    this.uuid = value;
  }

  public static ProductId withoutId() {
    return new ProductId(UUID.randomUUID());
  }
}
