package me.romeralvarez.intellizenmarketmate.product.application;

import me.romeralvarez.intellizenmarketmate.product.domain.events.ProductCreated;
import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({ProductCreated.class})
public class ProductCreatedEventHandler {

  @EventListener
  public void on(ProductCreated event) {
    System.out.println("Received ProductCreated event:");
    System.out.println("Product ID: " + event.aggregateId());
    System.out.println("Product Name: " + event.getName());
    System.out.println("Product Description: " + event.getDescription());
    System.out.println("Product Price: " + event.getPrice());
    System.out.println("Created At: " + event.getCreatedAt());
    System.out.println("Updated At: " + event.getUpdatedAt());
  }
}
