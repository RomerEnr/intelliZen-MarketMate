package me.romeralvarez.intellizenmarketmate.product.domain;

import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductDescription;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductId;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductName;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductPrice;

import java.time.LocalDateTime;

public class Product {
  private final ProductId id;
  private ProductName name;
  private ProductDescription description;
  private final ProductPrice price;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Product(ProductId id, ProductName name, ProductDescription description, ProductPrice price, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
