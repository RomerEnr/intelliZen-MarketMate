package me.romeralvarez.intellizenmarketmate.product.infraestructure;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {
  private String name;
  private String description;
  private BigDecimal price;
}
