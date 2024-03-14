package me.romeralvarez.intellizenmarketmate.shared.infraestructure.spring;

import me.romeralvarez.intellizenmarketmate.shared.domain.Service;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.DomainEvent;
import me.romeralvarez.intellizenmarketmate.shared.domain.bus.event.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Service
public class SpringApplicationEventBus implements EventBus {
  private final ApplicationEventPublisher publisher;

  public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void publish(final List<DomainEvent> events) {
    events.forEach(this::publish);
  }

  private void publish(final DomainEvent event) {
    this.publisher.publishEvent(event);
  }
}
