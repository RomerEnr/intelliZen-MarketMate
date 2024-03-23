package me.romeralvarez.intellizenmarketmate.supermarket.domain;

import me.romeralvarez.intellizenmarketmate.shared.domain.vo.SupermarketId;
import me.romeralvarez.intellizenmarketmate.supermarket.domain.vo.SupermarketChain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Supermarket {
  private final SupermarketId supermarketId;
  private final SupermarketChain chain;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Supermarket(SupermarketId supermarketId, SupermarketChain chain, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.supermarketId = supermarketId;
    this.chain = chain;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public SupermarketChain getChain() {
    return chain;
  }
  public UUID getId() {
    return supermarketId.value();
  }
}
