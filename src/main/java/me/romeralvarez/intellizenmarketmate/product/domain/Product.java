package me.romeralvarez.intellizenmarketmate.product.domain;

import me.romeralvarez.intellizenmarketmate.product.domain.events.ProductCreated;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductDescription;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductId;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductName;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductPrice;
import me.romeralvarez.intellizenmarketmate.shared.domain.AggregateRoot;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product extends AggregateRoot {
  private final ProductId id;
  private ProductName name;
  private ProductDescription description;
  private final ProductPrice price;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  private Product(ProductId id, ProductName name, ProductDescription description, ProductPrice price, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static Product create(ProductId id, ProductName name, ProductDescription description, ProductPrice price, LocalDateTime createdAt, LocalDateTime updatedAt) {
    Product product = new Product(id, name, description, price, createdAt, updatedAt);
    product.record(new ProductCreated(id.getValue().toString(), name.value(), description.value(), price.value(), createdAt, updatedAt));
    return product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id.equals(product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
