package me.romeralvarez.intellizenmarketmate.product.application;

import me.romeralvarez.intellizenmarketmate.product.domain.Product;
import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {
  private final EventBus eventBus;

  @Autowired
  public ProductService(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void createProduct(Product product) {
    eventBus.publish(product.pullDomainEvents());
  }
}
