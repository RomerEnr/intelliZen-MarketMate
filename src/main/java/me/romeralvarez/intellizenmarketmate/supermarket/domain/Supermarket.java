package me.romeralvarez.intellizenmarketmate.supermarket.domain;

import me.romeralvarez.intellizenmarketmate.shared.domain.AggregateRoot;
import me.romeralvarez.intellizenmarketmate.shared.domain.vo.SupermarketId;
import me.romeralvarez.intellizenmarketmate.supermarket.domain.vo.SupermarketName;

import java.time.LocalDateTime;

public class Supermarket {
  private final SupermarketId supermarketId;
  private SupermarketName name;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Supermarket(SupermarketId supermarketId, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.supermarketId = supermarketId;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
